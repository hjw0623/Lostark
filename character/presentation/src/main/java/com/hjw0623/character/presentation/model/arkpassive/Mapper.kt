package com.hjw0623.character.presentation.model.arkpassive

import com.hjw0623.character.domain.model.arkpassive.ArkEffect
import com.hjw0623.character.domain.model.arkpassive.ArkPassive
import com.hjw0623.character.presentation.model.gear.removeHtmlTags
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

fun ArkPassive.toArkPassiveUi(): ArkPassiveUi {
    val pointList = this.points
    val evolutionPoint = pointList.firstOrNull { it.name == "진화" }?.value ?: 0
    val enlightenmentPoint = pointList.firstOrNull { it.name == "깨달음" }?.value ?: 0
    val leapPoint = pointList.firstOrNull { it.name == "도약" }?.value ?: 0

    val evolutionEffectList = this.effects.filter { it.name == "진화" }
        .map { it.toEffect() }
    val enlightenmentEffectList = this.effects.filter { it.name == "깨달음" }
        .map { it.toEffect() }
    val leapEffectList = this.effects.filter { it.name == "도약" }
        .map { it.toEffect() }

    return ArkPassiveUi(
        evolutionPoint = evolutionPoint,
        enlightenmentPoint = enlightenmentPoint,
        leapPoint = leapPoint,
        evolutionEffectList = evolutionEffectList,
        enlightenmentEffectList = enlightenmentEffectList,
        leapEffectList = leapEffectList
    )
}

fun ArkEffect.toEffect(): Effect {
    val json = Json { ignoreUnknownKeys = true}
    val data = json.decodeFromString<JsonData>(this.tooltip)
    val tier = extractTier(this.description) ?: ""
    val nameTag = data.element000.value.jsonPrimitive.content
    val skillTitle = json.decodeFromJsonElement<CommonSkillValue>(data.element001.value)
    val multiText = data.element002.value.jsonPrimitive.content
    val level = extractLevel(skillTitle.leftText)
    return Effect(
        type = removeHtmlTags(this.name),
        tier = tier,
        icon = this.icon,
        effect = nameTag,
        level = level ?: "",
        description = removeHtmlTags(multiText).removeSuffix("||")
    )
}

@Serializable
data class SlotData(
    val iconGrade: Int,
    val iconPath: String,
    val imagePath: String
)

@Serializable
data class CommonSkillValue(
    val leftText: String,
    val level: String,
    val name: String,
    val slotData: SlotData
)

@Serializable
data class Element(
    val type: String,
    val value: JsonElement
)

@Serializable
data class JsonData(
    @SerialName("Element_000") val element000: Element,
    @SerialName("Element_001") val element001: Element,
    @SerialName("Element_002") val element002: Element
)

fun extractLevel(text: String): String? {
    val regex = """<FONT COLOR='#FFD200'>(\d+)</FONT>""".toRegex()
    return regex.find(text)?.groups?.get(1)?.value
}

fun extractTier(text: String): String? {
    val regex = """(\d+티어)""".toRegex()
    return regex.find(text)?.value
}