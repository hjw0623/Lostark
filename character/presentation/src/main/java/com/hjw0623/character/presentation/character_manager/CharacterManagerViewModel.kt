package com.hjw0623.character.presentation.character_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi
import com.hjw0623.character.presentation.character_overview.util.getClassImg
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import com.hjw0623.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharacterManagerViewModel(
    private val localCharacterDataSource: LocalCharacterDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterManagerState())
    val state = _state

    private val _events = Channel<CharacterManagerEvent>()
    val events = _events.receiveAsFlow()

    private var hasErrorOccurred = false

    init {
        getAllList()
    }

    fun onAction(action: CharacterManagerAction) {
        when (action) {
            is CharacterManagerAction.OnCharacterDeleteClick -> {

            }
            CharacterManagerAction.OnCharacterSettingClick -> {

            }

            CharacterManagerAction.OnCharacterAddClick -> {

            }
        }

    }

    private fun getAllList() {
        viewModelScope.launch {
            _state.update { it.copy(isCharacterProfileLoading = true) }

            localCharacterDataSource.getCharacters()
                .collect { characters ->
                    val characterProgressList = characters.map { character ->
                        val selectedRaids = localCharacterDataSource.getSelectedRaids(character.characterName).first()

                        val totalGold = selectedRaids.sumOf { it.maxGoldReward }
                        val earnedGold = selectedRaids.filter { it.isCompleted }.sumOf { it.maxGoldReward }

                        CharacterProgressUi(
                            icon = getClassImg(character.className),
                            server = character.server,
                            name = character.characterName,
                            className = character.className,
                            avgItemLevel = character.avgItemLevel.toString(),
                            totalGold = totalGold,
                            earnedGold = earnedGold
                        )
                    }

                    val groupedByServer = characterProgressList.groupBy { it.server }

                    val sortedByServer = groupedByServer
                        .entries
                        .sortedByDescending { serverGroup ->
                            serverGroup.value.maxOfOrNull { it.avgItemLevel.toDoubleOrNull() ?: 0.0 } ?: 0.0
                        }

                    _state.update {
                        it.copy(
                            isCharacterProfileLoading = false,
                            savedCharacterProgressListByServer = sortedByServer
                        )
                    }
                }
        }
    }



    private fun sendError(message: UiText) {
        viewModelScope.launch {
            if (!hasErrorOccurred) {
                hasErrorOccurred = true
                _events.send(CharacterManagerEvent.Error(message))
            }
        }
    }
}
