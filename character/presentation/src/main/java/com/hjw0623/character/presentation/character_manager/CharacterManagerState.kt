package com.hjw0623.character.presentation.character_manager

import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi

data class CharacterManagerState(
    val isCharacterProfileLoading: Boolean = false,
    val raidProgress: Float = 0.0F,
    val totalGold: Int = 0,
    val totalEarnedGold: Int = 0,
    val savedCharacterProgressListByServer: List<Map.Entry<String, List<CharacterProgressUi>>> = emptyList()
)