package com.hjw0623.data.model.engravings

import com.hjw0623.character.domain.model.engravings.ArkPassiveEffect
import com.hjw0623.character.domain.model.engravings.EngravingEffect
import com.hjw0623.character.domain.model.engravings.Engraving
import com.hjw0623.character.domain.model.engravings.Engravings

fun EngravingEffectSerializable.toDomain(): EngravingEffect {
    return EngravingEffect(
        icon = this.icon,
        name = this.name,
        description = this.description
    )
}

fun ArkPassiveEffectSerializable.toDomain(): ArkPassiveEffect {
    return ArkPassiveEffect(
        abilityStoneLevel = this.abilityStoneLevel,
        grade = this.grade,
        level = this.level,
        name = this.name,
        description = this.description
    )
}

fun EngravingSerializable.toDomain(): Engraving {
    return Engraving(
        slot = this.slot,
        name = this.name,
        icon = this.icon,
        tooltip = this.tooltip
    )
}

fun EngravingsSerializable.toDomain(): Engravings {
    return Engravings(
        engravings = this.engravings?.map { it.toDomain() } ?: emptyList(),
        engravingEffects = this.effects?.map { it.toDomain() } ?: emptyList(),
        arkPassiveEffect = this.arkPassiveEffects?.map { it.toDomain() } ?: emptyList()
    )
}