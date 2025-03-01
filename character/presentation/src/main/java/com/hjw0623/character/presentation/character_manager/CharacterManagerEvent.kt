package com.hjw0623.character.presentation.character_manager

import com.hjw0623.core.presentation.ui.UiText

sealed interface CharacterManagerEvent {
    data class Error(val error: UiText): CharacterManagerEvent
}