package com.hjw0623.character.domain.model.arkpassive

data class ArkPassive(
    val isArkPassive: Boolean,
    val points: List<ArkPoint>,
    val effects: List<ArkEffect>
)
