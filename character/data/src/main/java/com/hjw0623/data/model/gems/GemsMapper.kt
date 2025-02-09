package com.hjw0623.data.model.gems

import com.hjw0623.character.domain.model.gems.Effects
import com.hjw0623.character.domain.model.gems.Gem
import com.hjw0623.character.domain.model.gems.Gems
import com.hjw0623.character.domain.model.gems.Skill

fun GemsSerializable.toDomain(): Gems {
    return Gems(
        effects = this.effects.toDomain(),
        gems = this.gems?.map { it.toDomain() } ?: emptyList()
    )
}

fun EffectsSerializable.toDomain(): Effects {
    return Effects(
        description = this.description,
        skills = this.skills.map { it.toDomain() }
    )
}

fun SkillSerializable.toDomain(): Skill {
    return Skill(
        description = this.description,
        gemSlot = this.gemSlot,
        icon = this.icon,
        name = this.name,
        option = this.option,
        tooltip = this.tooltip ?: ""
    )
}

fun GemSerializable.toDomain(): Gem {
    return Gem(
        grade = this.grade,
        icon = this.icon,
        level = this.level,
        name = this.name,
        slot = this.slot,
        tooltip = this.tooltip ?: ""
    )
}