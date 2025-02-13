package com.hjw0623.data.model.skill

import com.hjw0623.character.domain.model.skill.Rune
import com.hjw0623.character.domain.model.skill.Skill
import com.hjw0623.character.domain.model.skill.Tripod

fun RuneSerializable.toDomain(): Rune {
    return Rune(
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        tooltip = this.tooltip
    )
}
fun TripodSerializable.toDomain(): Tripod {
    return Tripod(
        tier = this.tier,
        slot = this.slot,
        name = this.name,
        icon = this.icon,
        level = this.level,
        isSelected = this.isSelected,
        tooltip = this.tooltip
    )
}

fun SkillSerializable.toDomain(): Skill {
    return Skill(
        name = this.name,
        icon = this.icon,
        level = this.level,
        type = this.type,
        skillType = this.skillType,
        tripods = this.tripods.map { it.toDomain() },
        rune = this.rune?.toDomain()?: Rune(
            name = "",
            icon = "",
            grade = "",
            tooltip = ""
        ),
        tooltip = this.tooltip
    )
}