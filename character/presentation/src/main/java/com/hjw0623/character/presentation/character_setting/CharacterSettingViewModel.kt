package com.hjw0623.character.presentation.character_setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.domain.model.raid.RaidData
import com.hjw0623.character.domain.model.raid.RaidInfo
import com.hjw0623.character.domain.model.raid.toRaidInfo
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import com.hjw0623.core.domain.character.SelectedRaid
import com.hjw0623.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterSettingViewModel(
    private val localCharacterDataSource: LocalCharacterDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterSettingState())
    val state = _state

    private val _events = Channel<CharacterSettingEvent>()
    val events = _events.receiveAsFlow()

    fun setCharacterName(characterName: String) {
        viewModelScope.launch {
            _state.update { it.copy(characterName = characterName) }
            loadCharacterAndRaids(characterName)
        }
    }

    fun onAction(action: CharacterSettingAction) {
        when (action) {
            CharacterSettingAction.OnBackClick -> viewModelScope.launch {
                _events.send(CharacterSettingEvent.NavigateBack)
            }

            is CharacterSettingAction.OnRaidSelected -> toggleRaidSelection(action.raid)

            CharacterSettingAction.OnSaveClick -> saveSelectedRaids()
        }
    }

    private fun toggleRaidSelection(raid: RaidInfo) {
        val currentSelection = _state.value.selectedRaids.toMutableList()

        val isSameRaidSelected = currentSelection.any { it.raidName == raid.raidName }

        if (currentSelection.contains(raid)) {
            currentSelection.remove(raid)
        } else if (isSameRaidSelected) {
            sendError(UiText.DynamicString("이미 ${raid.raidName}의 다른 난이도를 선택하셨습니다."))
            return
        } else if (currentSelection.size < 3) {
            currentSelection.add(raid)
        } else {
            sendError(UiText.DynamicString("최대 3개의 레이드만 선택할 수 있습니다."))
            return
        }

        _state.update { it.copy(selectedRaids = currentSelection) }
    }


    private fun saveSelectedRaids() {
        viewModelScope.launch {
            val characterId = _state.value.characterName
            if (characterId.isBlank()) return@launch

            localCharacterDataSource.deleteSelectedRaids(characterId)

            val selectedRaids = _state.value.selectedRaids.map { raid ->
                SelectedRaid(
                    id = 0L,
                    characterId = _state.value.characterName,
                    raidName = raid.raidName,
                    difficulty = raid.difficulty,
                    gateProgress = List(raid.gateCount) { false },
                    gateRewards = raid.gateRewards
                )
            }

            selectedRaids.forEach { raid ->
                localCharacterDataSource.upsertSelectedRaid(raid)
            }

            _events.send(CharacterSettingEvent.RaidSavedSuccess)
        }
    }

    private fun loadCharacterAndRaids(characterName: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val character = localCharacterDataSource.getCharacter(characterName)
            if (character != null) {
                val availableRaids = RaidData.getRaidsByItemLevel(character.avgItemLevel)
                val selectedRaids = localCharacterDataSource.getSelectedRaids(characterName)
                    .first()
                    .map { it.toRaidInfo() }

                _state.update {
                    it.copy(
                        characterName = character.characterName,
                        availableRaids = availableRaids,
                        selectedRaids = selectedRaids,
                        isLoading = false
                    )
                }
            } else {
                _state.update { it.copy(isLoading = false) }
                sendError(UiText.DynamicString("캐릭터 정보를 불러올 수 없습니다."))
            }
        }
    }

    private fun sendError(message: UiText) {
        viewModelScope.launch {
                _events.send(CharacterSettingEvent.Error(message))
        }
    }
}
