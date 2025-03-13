package com.hjw0623.character.presentation.character_manager

import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi

data class CharacterManagerState(
    val isCharacterProfileLoading: Boolean = false,
    val showDialog: Boolean = false,
    val raidProgress: Float = 0.0F,
    val totalGold: Int = 0,
    val totalEarnedGold: Int = 0,
    val savedCharacterProgressListByServer: Map<String, List<CharacterProgressUi>> = emptyMap(),
    val characterToDelete: String = "",
    val selectedCharacterForGate: CharacterProgressUi? = null,
    val selectedRaidGateProgress: Map<String, List<Boolean>> = emptyMap(),
    val showGateSelectDialog: Boolean = false
)