package com.hjw0623.character.presentation.character_manager

import com.hjw0623.core.presentation.ui.UiText

sealed interface ManagerEvent {
    data class Error(val error: UiText): ManagerEvent
}