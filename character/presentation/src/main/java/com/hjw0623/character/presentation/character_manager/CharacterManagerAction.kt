package com.hjw0623.character.presentation.character_manager


sealed interface CharacterManagerAction {
    data class OnCharacterDeleteClick(val character: String): CharacterManagerAction
    data class OnShowDialog(val character: String): CharacterManagerAction
    data object OnDismissDeleteClick: CharacterManagerAction
    data object OnCharacterAddClick: CharacterManagerAction
    data class OnCharacterSettingClick(val characterName: String): CharacterManagerAction

    data class OnCharacterClick(val characterName: String) : CharacterManagerAction
    data class OnGateToggled(val characterName: String, val raidName: String, val gateIndex: Int) : CharacterManagerAction
    data object OnSaveGateSelection : CharacterManagerAction
    data object OnDismissGateSelection : CharacterManagerAction
}