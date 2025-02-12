package com.hjw0623.data.model.arkpassive

import com.hjw0623.character.domain.model.arkpassive.ArkEffect
import com.hjw0623.character.domain.model.arkpassive.ArkPassive
import com.hjw0623.character.domain.model.arkpassive.ArkPoint

fun ArkEffectDto.toDomain(): ArkEffect {
    return ArkEffect(
        name = this.name,
        description = this.description,
        icon = this.icon,
        tooltip = this.tooltip
    )
}

fun ArkPointDto.toDomain(): ArkPoint {
    return ArkPoint(
        name = this.name,
        value = this.value,
        tooltip = this.tooltip
    )
}

fun ArkPassiveDto.toDomain(): ArkPassive {
    return ArkPassive(
        isArkPassive = this.isArkPassive,
        points = this.points.map { it.toDomain() },
        effects = this.effects.map { it.toDomain() }
    )
}