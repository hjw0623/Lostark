package com.hjw0623.character.presentation.character_add

import com.hjw0623.character.presentation.character_add.model.CharacterAddUi

interface CharacterAddAction {
    data class OnSearchClick(val characterName: String): CharacterAddAction
    data class OnAddCharacterClick(val character: CharacterAddUi): CharacterAddAction
    data class OnShowDialog(val character: CharacterAddUi): CharacterAddAction
    data object OnDismissAddClick: CharacterAddAction
    data object OnClearQueryClick: CharacterAddAction
    data object OnBackClick: CharacterAddAction

}