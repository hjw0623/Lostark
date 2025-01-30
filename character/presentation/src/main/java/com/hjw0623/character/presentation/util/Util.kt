package com.hjw0623.character.presentation.util

fun shortPolishingEffect(polishingEffect: String): String {
    return when {
        polishingEffect.contains("추가 피해") -> "추피"
        polishingEffect.contains("주는 피해") -> "적추피"
        polishingEffect.contains("게이지") -> "서폿 아덴"
        polishingEffect.contains("낙인력") -> "낙인력"
        polishingEffect.contains("최대 생명력") -> "최생"
        polishingEffect.contains("무기 공격력") -> "무공"
        polishingEffect.contains("공격력") -> "공격력"
        polishingEffect.contains("최대 마나") -> "최마"
        polishingEffect.contains("상태이상") -> "상태이상"
        polishingEffect.contains("회복량") -> "전투회복"
        polishingEffect.contains("파티 회복") -> "파티회복"
        polishingEffect.contains("보호막") -> "파티보호"
        polishingEffect.contains("적중률") -> "치적"
        polishingEffect.contains("치명타 피해") -> "치피"
        polishingEffect.contains("아군 공격력") -> "아군공격"
        else -> polishingEffect
    }
}

fun getEffectLevel(effect: String): String {
    val effectMapping = mapOf(
        "추가 피해 +0.70%" to "하", "추가 피해 +1.60%" to "중", "추가 피해 +2.60%" to "상",
        "적에게 주는 피해 +0.55%" to "하", "적에게 주는 피해 +1.20%" to "중", "적에게 주는 피해 +2.00%" to "상",
        "세레나데, 신앙, 조화 게이지 획득량 +1.60%" to "하", "세레나데, 신앙, 조화 게이지 획득량 +3.60%" to "중", "세레나데, 신앙, 조화 게이지 획득량 +6.00%" to "상",
        "낙인력 +2.15%" to "하", "낙인력 +4.80%" to "중", "낙인력 +8.00%" to "상",
        "최대 생명력 +1300" to "하", "최대 생명력 +3250" to "중", "최대 생명력 +6500" to "상",
        "공격력 +80" to "하", "공격력 +195" to "중", "공격력 +390" to "상",
        "무기 공격력 +195" to "하", "무기 공격력 +480" to "중", "무기 공격력 +960" to "상",
        "최대 마나 +6" to "하", "최대 마나 +15" to "중", "최대 마나 +30" to "상",
        "상태이상 공격 지속시간 +0.20%" to "하", "상태이상 공격 지속시간 +0.50%" to "중", "상태이상 공격 지속시간 +1.00%" to "상",
        "전투 중 생명력 회복량 +10" to "하", "전투 중 생명력 회복량 +25" to "중", "전투 중 생명력 회복량 +50" to "상",

        "공격력 +0.40%" to "하", "공격력 +0.95%" to "중", "공격력 +1.55%" to "상",
        "무기 공격력 +0.80%" to "하", "무기 공격력 +1.80%" to "중", "무기 공격력 +3.00%" to "상",
        "파티원 회복 효과 +0.95%" to "하", "파티원 회복 효과 +2.10%" to "중", "파티원 회복 효과 +3.50%" to "상",
        "파티원 보호막 효과 +0.95%" to "하", "파티원 보호막 효과 +2.10%" to "중", "파티원 보호막 효과 +3.50%" to "상",

        "치명타 적중률 +0.40%" to "하", "치명타 적중률 +0.95%" to "중", "치명타 적중률 +1.55%" to "상",
        "치명타 피해 +1.10%" to "하", "치명타 피해 +2.40%" to "중", "치명타 피해 +4.00%" to "상",
        "아군 공격력 강화 효과 +1.35%" to "하", "아군 공격력 강화 효과 +3.00%" to "중", "아군 공격력 강화 효과 +5.00%" to "상",
        "아군 피해량 강화 효과 +2.00%" to "하", "아군 피해량 강화 효과 +4.50%" to "중", "아군 피해량 강화 효과 +7.50%" to "상" // 수정됨
    )

    return effectMapping[effect] ?: "X"
}




