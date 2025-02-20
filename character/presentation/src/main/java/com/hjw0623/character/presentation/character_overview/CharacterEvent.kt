package com.hjw0623.character.presentation.character_overview

import com.hjw0623.core.presentation.ui.UiText

sealed interface CharacterEvent {
    data class Error(val error: UiText): CharacterEvent
}