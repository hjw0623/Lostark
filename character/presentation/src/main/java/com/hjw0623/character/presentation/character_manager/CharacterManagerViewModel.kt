package com.hjw0623.character.presentation.character_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi
import com.hjw0623.character.presentation.character_overview.util.getClassImg
import com.hjw0623.core.domain.character.LocalCharacterDataSource
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

    init {
        getAllList()
    }

    fun onAction(action: CharacterManagerAction) {
        when(action) {
            CharacterManagerAction.OnCharacterAddClick -> {
                viewModelScope.launch {
                    _events.send(CharacterManagerEvent.NavigateToCharacterAdd)
                }
            }
            is CharacterManagerAction.OnCharacterSettingClick -> {
                viewModelScope.launch {
                    _events.send(CharacterManagerEvent.NavigateToCharacterSetting(action.characterName))
                }
            }
            is CharacterManagerAction.OnCharacterDeleteClick -> {
                deleteCharacter(action.character)
                _state.update {
                    it.copy(
                        showDialog = false,
                        characterToDelete = ""
                    )
                }
            }
            CharacterManagerAction.OnDismissDeleteClick -> {
                _state.update {
                    it.copy(
                        showDialog = false,
                        characterToDelete = ""
                    )
                }
            }
            is CharacterManagerAction.OnShowDialog -> {
                _state.update {
                    it.copy(
                        showDialog = true,
                        characterToDelete = action.character
                    )
                }
            }
        }

    }

    private fun deleteCharacter(character: String) {
        viewModelScope.launch {
            localCharacterDataSource.deleteCharacter(character)
            _events.send(CharacterManagerEvent.OnDeleteCharacter(character))
        }
    }

    private fun getAllList() {
        viewModelScope.launch {
            _state.update { it.copy(isCharacterProfileLoading = true) }

            localCharacterDataSource.getCharacters()
                .collect { characters ->
                    val characterProgressList = characters.map { character ->
                        val selectedRaids = localCharacterDataSource.getSelectedRaids(character.characterName).first()

                        CharacterProgressUi(
                            icon = getClassImg(character.className),
                            server = character.server,
                            name = character.characterName,
                            className = character.className,
                            avgItemLevel = character.avgItemLevel.toString(),
                            totalGold = 0,
                            earnedGold = 0
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
}
