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