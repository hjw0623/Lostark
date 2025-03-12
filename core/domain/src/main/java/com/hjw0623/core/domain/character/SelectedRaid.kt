package com.hjw0623.core.domain.character

data class SelectedRaid(
    val id: Long = 0,
    val characterId: String,
    val raidName: String,
    val difficulty: String,
    val gateProgress: List<Boolean>,
    val gateRewards: List<Int>
)
