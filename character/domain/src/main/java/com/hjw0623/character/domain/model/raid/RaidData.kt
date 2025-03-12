package com.hjw0623.character.domain.model.raid

object RaidData {
    val raids = listOf(
        RaidInfo("3막 칠흑 폭풍의 밤", "노말", 1680, 3, listOf(6000, 9500, 12500)),
        RaidInfo("3막 칠흑 폭풍의 밤", "하드", 1700, 3, listOf(7000, 11000, 20000)),
        RaidInfo("2막 아브렐슈드", "노말", 1670, 2, listOf(8500, 16500)),
        RaidInfo("2막 아브렐슈드", "하드", 1690, 2, listOf(10000, 20500)),
        RaidInfo("1막 에기르", "노말", 1660, 2, listOf(7500, 15500)),
        RaidInfo("1막 에기르", "하드", 1680, 2, listOf(9000, 18500)),
        RaidInfo("에키드나", "노말", 1620, 2, listOf(5000, 9500)),
        RaidInfo("에키드나", "하드", 1640, 2, listOf(6000, 12500)),
        RaidInfo("베히모스", "노말", 1640, 2, listOf(6000, 12500)),
        RaidInfo("카멘", "노말", 1610, 3, listOf(2500, 3000, 4500)),
        RaidInfo("카멘", "하드", 1630, 4, listOf(3500, 4500, 7500, 8000)),
        RaidInfo("일리아칸", "노말", 1580, 3, listOf(1000, 1800, 2600)),
        RaidInfo("일리아칸", "하드", 1600, 3, listOf(1500, 2500, 3500)),
        RaidInfo("아브렐슈드", "노말", 1490, 4, listOf(1000, 1000, 1000, 1600)),
        RaidInfo("아브렐슈드", "하드", 1490, 4, listOf(1200, 1200, 1200, 2000)),
        RaidInfo("쿠크세이트", "노말", 1475, 3, listOf(600, 900, 1500)),
        RaidInfo("비아키스", "노말", 1430, 2, listOf(600, 1000)),
        RaidInfo("비아키스", "하드", 1460, 2, listOf(900, 1500)),
        RaidInfo("발탄", "노말", 1415, 2, listOf(500, 700)),
        RaidInfo("발탄", "하드", 1445, 2, listOf(700, 1100)),
        RaidInfo("혼돈의 상아탑", "노말", 1600, 3, listOf(1500, 2000, 3000)),
        RaidInfo("혼돈의 상아탑", "하드", 1620, 3, listOf(2000, 3000, 5500)),
        RaidInfo("카양겔", "노말", 1540, 3, listOf(800, 1200, 1600)),
        RaidInfo("카양겔", "하드", 1580, 3, listOf(1000, 1600, 2200)),
        RaidInfo("아르고스", "노말", 1370, 3, listOf(300, 300, 400))
    )

    fun getRaidsByItemLevel(itemLevel: Double): List<RaidInfo> {
        return raids.filter { it.itemLevel <= itemLevel }
    }
}