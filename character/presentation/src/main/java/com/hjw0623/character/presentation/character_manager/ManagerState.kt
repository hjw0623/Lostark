package com.hjw0623.character.presentation.character_manager

import com.hjw0623.character.presentation.character_manager.mockup.mockCharacterProgressContent
import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi

data class ManagerState(
    val isCharacterProfileLoading: Boolean = false,
    val searchedCharacterName: String = "",
    val raidProgress: Float = 0.3f,
    val totalGold: Int = 50000,
    val totalEarnedGold: Int = 12000,
    //val savedCharacterProgressList: List<CharacterProgressUI> = emptyList(),
    val savedCharacterProgressList: List<CharacterProgressUi> = listOf(
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
        mockCharacterProgressContent(),
    ),
    //val userCharacter: List<CharacterUi> = emptyList()
)