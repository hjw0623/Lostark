package com.hjw0623.character.presentation.character_manager

import com.hjw0623.core.presentation.ui.UiText

sealed interface CharacterManagerEvent {
    data class Error(val error: UiText): CharacterManagerEvent
    data class OnDeleteCharacter(val characterName: String): CharacterManagerEvent
    data object NavigateToCharacterAdd: CharacterManagerEvent
    data class NavigateToCharacterSetting(val characterName: String): CharacterManagerEvent
}