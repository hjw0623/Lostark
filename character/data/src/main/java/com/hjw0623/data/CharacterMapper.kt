package com.hjw0623.data

import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.domain.model.gems.Effects
import com.hjw0623.character.domain.model.gems.Gem
import com.hjw0623.character.domain.model.gems.Gems
import com.hjw0623.character.domain.model.gems.Skill
import com.hjw0623.character.domain.model.profile.CharacterProfile
import com.hjw0623.character.domain.model.profile.Stat
import com.hjw0623.character.domain.model.profile.Tendency
import com.hjw0623.data.model.gear.GearSerializable
import com.hjw0623.data.model.gems.EffectsSerializable
import com.hjw0623.data.model.gems.GemSerializable
import com.hjw0623.data.model.gems.GemsSerializable
import com.hjw0623.data.model.gems.SkillSerializable
import com.hjw0623.data.model.profile.CharacterProfileSerializable

fun CharacterProfileSerializable.toDomain(): CharacterProfile {
    return CharacterProfile(
        characterImage = this.characterImage,
        expeditionLevel = this.expeditionLevel,
        pvpGradeName = this.pvpGradeName,
        townLevel = this.townLevel,
        townName = this.townName,
        title = this.title,
        guildMemberGrade = this.guildMemberGrade,
        guildName = this.guildName,
        usingSkillPoint = this.usingSkillPoint,
        totalSkillPoint = this.totalSkillPoint,
        stats = this.stats.map { Stat(it.type, it.value, it.tooltip) },
        tendencies = this.tendencies.map { Tendency(it.type, it.point, it.maxPoint) },
        serverName = this.serverName,
        characterName = this.characterName,
        characterLevel = this.characterLevel,
        characterClassName = this.characterClassName,
        itemAvgLevel = this.itemAvgLevel,
        itemMaxLevel = this.itemMaxLevel
    )
}
fun GearSerializable.toDomain(): Gear {
    return Gear(
        type = this.type,
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        tooltip = this.tooltip
    )
}
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
