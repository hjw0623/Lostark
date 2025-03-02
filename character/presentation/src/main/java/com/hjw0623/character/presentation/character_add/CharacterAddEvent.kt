package com.hjw0623.character.presentation.character_add

import com.hjw0623.character.presentation.character_add.model.CharacterAddUi
import com.hjw0623.core.presentation.ui.UiText

interface CharacterAddEvent {
    data class Error(val error: UiText): CharacterAddEvent
    data class AddCharacter(val character: CharacterAddUi) : CharacterAddEvent
    data object NavigateBack: CharacterAddEvent
}