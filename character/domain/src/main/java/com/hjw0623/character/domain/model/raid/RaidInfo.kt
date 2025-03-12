package com.hjw0623.character.domain.model.raid

import com.hjw0623.core.domain.character.SelectedRaid

data class RaidInfo(
    val raidName: String,
    val difficulty: String,
    val itemLevel: Int,
    val gateCount: Int,
    val gateRewards: List<Int>
)

fun SelectedRaid.toRaidInfo(): RaidInfo {
    val raidInfo = RaidData.raids.firstOrNull { it.raidName == this.raidName && it.difficulty == this.difficulty }

    return RaidInfo(
        raidName = this.raidName,
        difficulty = this.difficulty,
        itemLevel = raidInfo?.itemLevel ?: 0,
        gateCount = gateProgress.size,
        gateRewards = gateRewards
    )
}
