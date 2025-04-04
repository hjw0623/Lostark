package com.hjw0623.character.presentation.character_overview.model.skill

import com.hjw0623.character.domain.model.skill.Rune
import com.hjw0623.character.domain.model.skill.Skill
import com.hjw0623.character.domain.model.skill.Tripod
import com.hjw0623.character.presentation.character_overview.model.gear.removeHtmlTags
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun Skill.toSkillUi(): SkillUi {
    val rune = this.rune?.toRuneUi()

    val skillInfoJson = Json.parseToJsonElement(this.tooltip).jsonObject

    val name = skillInfoJson["Element_000"]?.jsonObject
        ?.get("value")?.jsonPrimitive?.content ?: ""

    val skillType = skillInfoJson["Element_001"]?.jsonObject
        ?.get("value")?.jsonObject
        ?.get("level")?.jsonPrimitive?.content ?: ""

    val castingType = skillInfoJson["Element_001"]?.jsonObject
        ?.get("value")?.jsonObject
        ?.get("name")?.jsonPrimitive?.content ?: ""

    val cooldownTime = skillInfoJson["Element_001"]?.jsonObject
        ?.get("value")?.jsonObject
        ?.get("leftText")?.jsonPrimitive?.content ?: ""

    val mana = skillInfoJson["Element_004"]?.jsonObject
        ?.get("value")?.jsonPrimitive?.content ?: ""

    val description = skillInfoJson["Element_005"]?.jsonObject
        ?.get("value")?.jsonPrimitive?.content ?: ""

    val neutralize = Regex("무력화 : (.*?)<").find(description)?.groupValues?.get(1) ?: ""

    val attackType = Regex("공격 타입 : (.*?)<").find(description)?.groupValues?.get(1) ?: ""

    val superArmor = Regex("슈퍼아머 : (.*?)<").find(description)?.groupValues?.get(1) ?: ""

    val partBreak = Regex("부위 파괴 : (.*?)<").find(description)?.groupValues?.get(1) ?: ""

    val firstTripod = this.tripods
        .filter { it.isSelected }.filter { it.tier == 0 }.firstOrNull()?.toTripodUi()
    val secondTripod = this.tripods
        .filter { it.isSelected }.filter { it.tier == 1 }.firstOrNull()?.toTripodUi()
    val thirdTripod = this.tripods
        .filter { it.isSelected }.filter { it.tier == 2 }.firstOrNull()?.toTripodUi()
    return SkillUi(
        name = this.name,
        icon = this.icon,
        rune = rune,
        skillLevel = this.level,
        firstTripod = firstTripod,
        secondTripod = secondTripod,
        thirdTripod = thirdTripod,
        skillInfo = SkillInfoUi(
            name = removeHtmlTags(name),
            skillType = removeHtmlTags(skillType),
            castingType = removeHtmlTags(castingType),
            cooldownTime = removeHtmlTags(cooldownTime),
            mana = removeHtmlTags(mana).removeSuffix("|"),
            description = removeHtmlTags(description).cleanSkillDescription(),
            neutralize = removeHtmlTags(neutralize),
            attackType = removeHtmlTags(attackType),
            superArmor = removeHtmlTags(superArmor),
            partBreak = removeHtmlTags(partBreak)
        ),
    )
}


fun Rune.toRuneUi(): RuneUi {
    if (this.tooltip.isNullOrBlank()) {
        return RuneUi(
            name = this.name,
            icon = this.icon,
            grade = this.grade,
            description = "정보 없음"
        )
    }

    val tooltipJson = try {
        Json.parseToJsonElement(this.tooltip).jsonObject
    } catch (e: Exception) {
        return RuneUi(
            name = this.name,
            icon = this.icon,
            grade = this.grade,
            description = "정보 없음"
        )
    }

    val description = tooltipJson["Element_002"]?.jsonObject
        ?.get("value")?.jsonObject
        ?.get("Element_001")?.jsonPrimitive?.content ?: "정보 없음"

    return RuneUi(
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        description = description
    )
}
fun Tripod.toTripodUi(): TripodUi {
    return TripodUi(
        name = this.name,
        icon = this.icon,
        level = this.level.toString(),
        description = removeHtmlTags(this.tooltip)
    )
}

fun String.cleanSkillDescription(): String {
    return this.replace(Regex("(무력화|공격 타입|슈퍼아머|부위 파괴).*"), "").trim()
}