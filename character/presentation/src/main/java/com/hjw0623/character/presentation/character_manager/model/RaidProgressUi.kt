package com.hjw0623.character.presentation.character_manager.model

data class RaidProgressUi(
    val characterId: String,
    val raidName: String,
    val difficulty: String,
    val gateProgress: List<Boolean>,
    val gateRewards: List<Int>,
)
