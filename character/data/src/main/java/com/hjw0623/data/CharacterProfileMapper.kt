package com.hjw0623.data

import com.hjw0623.character.domain.model.CharacterProfile
import com.hjw0623.character.domain.model.Stat
import com.hjw0623.character.domain.model.Tendency
import com.hjw0623.data.model.CharacterProfileSerializable

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