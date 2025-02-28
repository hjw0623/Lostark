package com.hjw0623.character.presentation.character_search

import com.hjw0623.core.presentation.ui.UiText

interface CharacterSearchEvent {
    data class Error(val error: UiText): CharacterSearchEvent
    data class NavigationToCharacterOverviewScreen(val characterName: String): CharacterSearchEvent
}