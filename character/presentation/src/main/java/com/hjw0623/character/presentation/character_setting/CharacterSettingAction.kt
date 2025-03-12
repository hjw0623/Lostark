package com.hjw0623.character.presentation.character_setting

import com.hjw0623.character.domain.model.raid.RaidInfo

sealed interface CharacterSettingAction {
    data object OnBackClick: CharacterSettingAction
    data object OnSaveClick: CharacterSettingAction
    data class OnRaidSelected(val raid: RaidInfo): CharacterSettingAction
}