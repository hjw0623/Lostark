package com.hjw0623.character.presentation.character_overview

import com.hjw0623.core.presentation.ui.UiText

sealed interface CharacterOverviewEvent {
    data class Error(val error: UiText): CharacterOverviewEvent
    data object NavigateBack: CharacterOverviewEvent
}