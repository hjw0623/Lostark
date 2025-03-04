package com.hjw0623.character.presentation.character_manager

import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi

sealed interface CharacterManagerAction {
    data class OnCharacterDeleteClick(val character: CharacterProgressUi): CharacterManagerAction
    data object OnCharacterAddClick: CharacterManagerAction
    data object OnCharacterSettingClick: CharacterManagerAction
}