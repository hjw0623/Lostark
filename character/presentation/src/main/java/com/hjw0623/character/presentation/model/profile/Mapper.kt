package com.hjw0623.character.presentation.model.profile

import com.hjw0623.character.domain.model.profile.CharacterProfile


fun CharacterProfile.toCharacterProfileUi(): CharacterProfileUi {
    return CharacterProfileUi(
        characterImage = this.characterImage,
        characterClassName = this.characterClassName,
        serverName = this.serverName,
        title = this.title ?: "",
        characterName = this.characterName,
        itemAvgLevel = this.itemAvgLevel,
        characterLevel = this.characterLevel,
        guildName = this.guildName ?: "",
        expeditionLevel = this.expeditionLevel,
        pvpGradeName = this.pvpGradeName,
        townLevel = this.townLevel ?: 0,
        townName = this.townName ?: ""
    )
}

fun CharacterProfile.toCharacterStatsUi(): StatsUi {
    val stats = this.stats

    return StatsUi(
        attackPoint = stats.find { it.type == "공격력" }?.value?.toInt() ?: 0,
        hp = stats.find { it.type == "최대 생명력" }?.value?.toInt() ?: 0,
        critical = stats.find { it.type == "치명" }?.value?.toInt() ?: 0,
        specialty = stats.find { it.type == "특화" }?.value?.toInt() ?: 0,
        agility = stats.find { it.type == "신속" }?.value?.toInt() ?: 0,
        subdue = stats.find { it.type == "제압" }?.value?.toInt() ?: 0,
        endurance = stats.find { it.type == "인내" }?.value?.toInt() ?: 0,
        proficiency = stats.find { it.type == "숙련" }?.value?.toInt() ?: 0
    )
}

