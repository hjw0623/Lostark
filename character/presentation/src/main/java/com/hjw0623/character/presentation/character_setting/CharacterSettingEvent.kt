package com.hjw0623.character.presentation.character_setting

import com.hjw0623.core.presentation.ui.UiText

sealed interface CharacterSettingEvent {
    data class Error(val error: UiText): CharacterSettingEvent
    data object RaidSavedSuccess: CharacterSettingEvent
    data object NavigateBack: CharacterSettingEvent
}