package com.hjw0623.character.presentation.character_manager


sealed interface CharacterManagerAction {
    data class OnCharacterDeleteClick(val character: String): CharacterManagerAction
    data class OnShowDialog(val character: String): CharacterManagerAction
    data object OnDismissDeleteClick: CharacterManagerAction
    data object OnCharacterAddClick: CharacterManagerAction
    data class OnCharacterSettingClick(val characterName: String): CharacterManagerAction
}