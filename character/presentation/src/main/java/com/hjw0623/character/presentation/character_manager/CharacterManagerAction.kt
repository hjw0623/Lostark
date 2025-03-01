package com.hjw0623.character.presentation.character_manager

sealed interface CharacterManagerAction {
    data object OnCharacterAddClick: CharacterManagerAction
    data object OnCharacterDeleteClick: CharacterManagerAction
    data object OnCharacterSettingClick: CharacterManagerAction
}