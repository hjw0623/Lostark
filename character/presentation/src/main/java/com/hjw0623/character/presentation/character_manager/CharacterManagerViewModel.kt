package com.hjw0623.character.presentation.character_manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi
import com.hjw0623.character.presentation.character_manager.model.RaidProgressUi
import com.hjw0623.character.presentation.character_overview.util.getClassImg
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import com.hjw0623.core.domain.character.SelectedRaid
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
        when (action) {
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

            is CharacterManagerAction.OnCharacterClick -> {
                val updatedList =
                    _state.value.savedCharacterProgressListByServer.mapValues { (_, characters) ->
                        characters.map { character ->
                            if (character.name == action.characterName) {
                                character.copy(isExpanded = !character.isExpanded)
                            } else {
                                character
                            }
                        }
                    }
                _state.update { it.copy(savedCharacterProgressListByServer = updatedList) }
            }

            is CharacterManagerAction.OnGateToggled -> {
                _state.update {
                    val updatedCharacters =
                        it.savedCharacterProgressListByServer.mapValues { (_, characters) ->
                            characters.map { character ->
                                if (character.name == action.characterName) {
                                    val updatedRaids = character.raids.map { raid ->
                                        if (raid.raidName == action.raidName) {
                                            val updatedGateProgress = raid.gateProgress.toMutableList()
                                            updatedGateProgress[action.gateIndex] =
                                                !updatedGateProgress[action.gateIndex]

                                            val updatedRaid = raid.copy(gateProgress = updatedGateProgress)

                                            viewModelScope.launch {
                                                val existingRaid = localCharacterDataSource.getSelectedRaid(
                                                    character.name,
                                                    updatedRaid.raidName
                                                )

                                                val newRaid = SelectedRaid(
                                                    id = existingRaid?.id ?: 0L,
                                                    characterId = character.name,
                                                    raidName = updatedRaid.raidName,
                                                    difficulty = updatedRaid.difficulty,
                                                    gateProgress = updatedRaid.gateProgress,
                                                    gateRewards = updatedRaid.gateRewards
                                                )

                                                localCharacterDataSource.upsertSelectedRaid(newRaid)
                                            }

                                            updatedRaid
                                        } else {
                                            raid
                                        }
                                    }
                                    val newEarnedGold = updatedRaids.sumOf { raid ->
                                        raid.gateProgress.zip(raid.gateRewards)
                                            .filter { (progress, _) -> progress  }
                                            .sumOf { (_, reward) -> reward }
                                    }
                                    character.copy(
                                        raids = updatedRaids,
                                        earnedGold = newEarnedGold
                                    )
                                } else {
                                    character
                                }
                            }
                        }
                    it.copy(savedCharacterProgressListByServer = updatedCharacters)
                }
            }

            CharacterManagerAction.OnSaveGateSelection -> {
                viewModelScope.launch {
                    state.value.selectedCharacterForGate?.let { character ->
                        character.raids.forEach { raid ->
                            val updatedGateProgress = state.value.selectedRaidGateProgress[raid.raidName] ?: return@forEach

                            val updatedRaid = raid.copy(
                                gateProgress = updatedGateProgress
                            )

                            localCharacterDataSource.upsertSelectedRaid(
                                SelectedRaid(
                                    id = 0L,
                                    characterId = updatedRaid.characterId,
                                    raidName = updatedRaid.raidName,
                                    difficulty = updatedRaid.difficulty,
                                    gateProgress = updatedRaid.gateProgress,
                                    gateRewards = updatedRaid.gateRewards
                                )
                            )
                        }

                        _state.update {
                            it.copy(
                                selectedCharacterForGate = null,
                                selectedRaidGateProgress = emptyMap(),
                                showGateSelectDialog = false
                            )
                        }
                    }
                }
            }

            CharacterManagerAction.OnDismissGateSelection -> {
                _state.update {
                    it.copy(
                        selectedCharacterForGate = null,
                        selectedRaidGateProgress = emptyMap(),
                        showGateSelectDialog = false
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
                        val totalGold = selectedRaids.sumOf { it.gateRewards.sum() }

                        val earnedGold = selectedRaids.sumOf { raid ->
                            raid.gateProgress.zip(raid.gateRewards)
                                .filter { (progress, _) -> progress }
                                .sumOf { (_, reward) -> reward }
                        }

                        CharacterProgressUi(
                            icon = getClassImg(character.className),
                            server = character.server,
                            name = character.characterName,
                            className = character.className,
                            avgItemLevel = character.avgItemLevel.toString(),
                            totalGold = totalGold,
                            earnedGold = earnedGold,
                            raids = selectedRaids.map { raid ->
                                RaidProgressUi(
                                    characterId = character.characterName,
                                    raidName = raid.raidName,
                                    difficulty = raid.difficulty,
                                    gateProgress = raid.gateProgress,
                                    gateRewards = raid.gateRewards
                                )
                            },
                            isExpanded = false,
                        )
                    }

                    val groupedByServer = characterProgressList.groupBy { it.server }

                    val sortedByServer = groupedByServer
                        .entries
                        .sortedByDescending { serverGroup ->
                            serverGroup.value.maxOfOrNull { it.avgItemLevel.toDoubleOrNull() ?: 0.0 } ?: 0.0
                        }
                        .associate { it.toPair() }

                    _state.update {
                        it.copy(
                            isCharacterProfileLoading = false,
                            savedCharacterProgressListByServer = sortedByServer,
                            totalGold = characterProgressList.sumOf { it.totalGold },
                            totalEarnedGold = characterProgressList.sumOf { it.earnedGold }
                        )
                    }
                }
        }
    }
}
