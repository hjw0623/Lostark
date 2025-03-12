package com.hjw0623.character.presentation.character_setting

import com.hjw0623.character.domain.model.raid.RaidInfo


data class CharacterSettingState(
    val isLoading: Boolean = false,
    val characterName: String = "",
    val availableRaids: List<RaidInfo> = emptyList(),
    val selectedRaids: List<RaidInfo> = emptyList(),
)
