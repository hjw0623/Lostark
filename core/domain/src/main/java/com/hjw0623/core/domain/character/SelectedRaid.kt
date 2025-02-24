package com.hjw0623.core.domain.character

data class SelectedRaid(
    val id: Long = 0,
    val characterId: String,
    val raidName: String,
    val difficulty: String,
    val maxGoldReward: Int,
    val isCompleted: Boolean = false
)
