package com.hjw0623.character.presentation.model.gear

import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.domain.model.gems.Gem
import com.hjw0623.character.presentation.util.tierFourAncientEffectWithGrades
import com.hjw0623.character.presentation.util.getEffectRank
import com.hjw0623.character.presentation.util.tierFourRelicEffectWithGrades
import com.hjw0623.character.presentation.util.tierThreeAncientEffectWithGrades
import com.hjw0623.character.presentation.util.tierThreeRelicEffectWithGrades
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

fun Gear.toGearUi(): GearUi {
    val tooltip = removeHtmlTags(this.tooltip)

    val quality = extractValueByKey(tooltip, "qualityValue").firstOrNull() ?: 0

    val transcendenceList = extractByPattern(tooltip, """\[초월\]""".toRegex()).mapNotNull {
        extractNumbersFromMatchedString(it)
    }
    val transcendencePair = transcendenceList.firstOrNull() ?: Pair(0, 0)

    val advancedUpgradeStep =
        extractByPattern(tooltip, """\[상급 재련\]""".toRegex()).firstOrNull() ?: ""

    val pattern = """^\[(${type}|공용)\]""".toRegex()
    val extractedElixirListByPattern = extractFilteredContentByPattern(tooltip, pattern)
    val elixirList = extractElixirFromList(extractedElixirListByPattern)
    val elixirSum = sumLevels(elixirList)
    return GearUi(
        iconUri = this.icon,
        quality = quality.toString().toInt(),
        grade = this.grade,
        transcendence = transcendencePair.second,
        transcendenceGrade = transcendencePair.first,
        advancedUpgradeStep = extractFirstNumber(advancedUpgradeStep) ?: 0,
        equipmentName = this.name,
        type = this.type,
        elixirList = elixirList,
        elixirSum = elixirSum
    )
}

fun Gear.toAccessoriesUi(): AccessoriesUi {
    val tooltip = removeHtmlTags(this.tooltip)

    val quality = extractValueByKey(tooltip, "qualityValue").firstOrNull() ?: 0

    val enlightenment = extractEnlightenmentFromJson(tooltip)

    val polishingEffectList = extractPolishingEffects(tooltip)

    val tier = extractItemTier(tooltip)
    return AccessoriesUi(
        iconUri = this.icon,
        quality = quality.toString().toInt(),
        grade = this.grade,
        tier = tier ?: 0,
        enlightenment = enlightenment,
        name = this.name,
        type = this.type,
        polishingEffectList = polishingEffectList
    )
}

fun Gear.toAbilityStoneUi(): AbilityStoneUi {
    val tooltip = removeHtmlTags(this.tooltip)

    val hpRegex = """체력 \+(\d+)""".toRegex()
    val hpValues = hpRegex.findAll(tooltip).map { it.groupValues[1].toInt() }.toList()

    val hp = hpValues.getOrNull(0) ?: 0  // 기본 체력
    val bonusHp = hpValues.getOrNull(1) ?: 0  // 보너스 체력

    val engravingRegex = """\[(.+?)\] (Lv\.\d+)""".toRegex()
    val engravingList = engravingRegex.findAll(tooltip)
        .map { matchResult -> "${matchResult.groupValues[1]} ${matchResult.groupValues[2]}" }
        .toList()

    val levelBonusRegex = """\[레벨 보너스\] (.+)""".toRegex()
    val levelBonus = levelBonusRegex.find(tooltip)?.groupValues?.get(1) ?: ""
    return AbilityStoneUi(
        type = this.type,
        name = this.name,
        iconUri = this.icon,
        grade = this.grade,
        hp = hp,
        bonusHp = bonusHp,
        engravingList = engravingList,
        levelBonus = levelBonus
    )
}

fun Gear.toBraceletUi(): BraceletUi {
    val jsonObject = JSONObject(tooltip)
    val itemEffects = jsonObject.getJSONObject("Element_004").getJSONObject("value").getString("Element_001")
    val regex = "(<img[^>]+>)".toRegex()

    val jsonSpecialEffectList = itemEffects.split(regex).filter { it.isNotBlank() }
    val specialEffect = jsonSpecialEffectList.map { removeHtmlTags(it).trim() }

    val specialEffectsList = mutableListOf<SpecialEffect>()
    val statsList = mutableListOf<String>()

    val tier = extractItemTier(tooltip)

    specialEffect.forEach { effectText ->
        val (effect, grade) = when {
            this.grade == "고대" && tier == 4 -> getEffectRank(tierFourAncientEffectWithGrades, effectText)
            this.grade == "유물" && tier == 4-> getEffectRank(tierFourRelicEffectWithGrades, effectText)
            this.grade == "고대" && tier == 3-> getEffectRank(tierThreeAncientEffectWithGrades, effectText)
            this.grade == "유물" && tier == 3-> getEffectRank(tierThreeRelicEffectWithGrades, effectText)
            else -> getEffectRank(tierFourAncientEffectWithGrades, effectText)
        }
        if (effect == grade) {
            statsList.add(effect)
        } else {
            specialEffectsList.add(SpecialEffect(effect = effect, grade = grade))
        }
    }
    return BraceletUi(
        type = this.type,
        name = this.name,
        iconUri = this.icon,
        grade = this.grade,
        tier = tier ?: 0,
        stats = statsList,
        specialEffect = specialEffectsList
    )
}

fun Gem.toGemsUi(): GemsUi {
    val skillAndEffect = extractGemEffectAndSkill(this.tooltip.toString())
    return GemsUi(
        name = this.name,
        icon = this.icon,
        level = this.level,
        grade = this.grade,
        skillName = skillAndEffect?.first ?: "",
        effect = skillAndEffect?.second ?: ""
    )
}

fun categorizeGears(gearList: List<Gear>): Map<String, List<Gear>> {
    val categorizedGears = gearList.groupBy { gear ->
        when (gear.type) {
            "무기", "투구", "상의", "하의", "장갑", "어깨" -> "장비"
            "목걸이", "귀걸이", "반지" -> "장신구"
            "어빌리티 스톤" -> "어빌리티 스톤"
            "팔찌" -> "팔찌"
            else -> "기타"
        }
    }

    return mapOf(
        "장비" to (categorizedGears["장비"] ?: emptyList()),
        "장신구" to (categorizedGears["장신구"] ?: emptyList()),
        "어빌리티 스톤" to (categorizedGears["어빌리티 스톤"] ?: emptyList()),
        "팔찌" to (categorizedGears["팔찌"] ?: emptyList()),
        "기타" to (categorizedGears["기타"] ?: emptyList())
    )
}

fun removeHtmlTags(input: String): String {
    return input.replace(Regex("<.*?>"), "")
}

fun extractByPattern(jsonString: String, pattern: Regex): List<String> {
    val result = mutableListOf<String>()

    try {
        val jsonObject = JSONObject(jsonString)

        fun searchValue(json: Any) {
            when (json) {
                is JSONObject -> {
                    for (key in json.keys()) {
                        searchValue(json.get(key))
                    }
                }

                is JSONArray -> {
                    for (i in 0 until json.length()) {
                        searchValue(json.get(i))
                    }
                }

                is String -> {
                    val match = pattern.find(json)
                    if (match != null) {
                        result.add(json)
                    }
                }
            }
        }

        searchValue(jsonObject)

    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return result
}

fun extractValueByKey(jsonString: String, key: String): List<Any> {
    val result = mutableListOf<Any>()

    try {
        val jsonObject = JSONObject(jsonString)

        fun searchKey(json: Any) {
            when (json) {
                is JSONObject -> {
                    for (k in json.keys()) {
                        if (k == key) {
                            result.add(json.get(k))
                        } else {
                            searchKey(json.get(k))
                        }
                    }
                }

                is JSONArray -> {
                    for (i in 0 until json.length()) {
                        searchKey(json.get(i))
                    }
                }
            }
        }

        searchKey(jsonObject)

    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return result
}

fun extractFilteredContentByPattern(jsonString: String, filterPattern: Regex): List<String> {
    val result = mutableListOf<String>()

    try {
        val jsonObject = JSONObject(jsonString)
        val stack: Stack<Any> = Stack()
        stack.push(jsonObject)

        while (stack.isNotEmpty()) {
            val current = stack.pop()

            when (current) {
                is JSONObject -> {
                    if (current.optBoolean("bPoint", false) && current.has("contentStr")) {
                        val value = current.getString("contentStr")
                        if (filterPattern.containsMatchIn(value)) {
                            result.add(value)
                        }
                    }
                    for (key in current.keys()) {
                        stack.push(current.get(key))
                    }
                }

                is JSONArray -> {
                    for (i in current.length() - 1 downTo 0) {
                        stack.push(current.get(i))
                    }
                }
            }
        }

    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return result
}

fun extractNumbersFromMatchedString(input: String): Pair<Int, Int>? {
    val numbers = """\d+""".toRegex().findAll(input).map { it.value.toInt() }.toList()
    return if (numbers.size >= 2) {
        Pair(numbers[0], numbers[1])
    } else {
        null
    }
}

fun extractFirstNumber(input: String): Int? {
    val match = """\d+""".toRegex().find(input)
    return match?.value?.toInt()
}

fun extractElixirFromList(dataList: List<String>): List<String> {
    val result = mutableListOf<String>()
    val statPattern = """([가-힣\s]+(?:\(\S+\))?)\sLv\.\d+""".toRegex()

    for (data in dataList) {
        val matches = statPattern.findAll(data)
        for (match in matches) {
            result.add(match.value)
        }
    }

    return result
}

fun extractEnlightenmentFromJson(jsonString: String): String {
    val enlightenmentPattern = """깨달음\s\+\d+""".toRegex()

    try {
        val jsonObject = JSONObject(jsonString)
        val queue: Queue<Any> = LinkedList()
        queue.add(jsonObject)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            when (current) {
                is JSONObject -> {
                    for (key in current.keys()) {
                        val value = current.get(key)
                        if (value is String && enlightenmentPattern.containsMatchIn(value)) {
                            return value //
                        } else {
                            queue.add(value)
                        }
                    }
                }

                is JSONArray -> {
                    for (i in 0 until current.length()) {
                        queue.add(current.get(i))
                    }
                }
            }
        }
    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return ""
}

fun extractPolishingEffects(jsonString: String): List<String> {
    val result = mutableListOf<String>()
    val queue: Queue<Any> = LinkedList()
    queue.add(JSONObject(jsonString))

    val statPattern = """[가-힣\s]+?\+\d+(\.\d+)?%?""".toRegex()

    try {
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            when (current) {
                is JSONObject -> {
                    for (key in current.keys()) {
                        val value = current.get(key)

                        if (value is JSONObject && value.has("Element_000") && value.has("Element_001")) {
                            val header = value.getString("Element_000")
                            val effectString = value.getString("Element_001")

                            if ("연마 효과" in header) {
                                statPattern.findAll(effectString)
                                    .map { it.value.trim() }
                                    .forEach { result.add(it) }
                            }
                        } else {
                            queue.add(value)
                        }
                    }
                }

                is JSONArray -> {
                    for (i in 0 until current.length()) {
                        queue.add(current.get(i))
                    }
                }
            }
        }
    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return result
}

fun extractItemTier(jsonString: String): Int? {
    val queue: Queue<Any> = LinkedList()
    queue.add(JSONObject(jsonString))

    val tierPattern = """아이템 티어\s(\d+)""".toRegex()

    try {
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            when (current) {
                is JSONObject -> {
                    for (key in current.keys()) {
                        val value = current.get(key)

                        if (value is String) {

                            val match = tierPattern.find(value)
                            if (match != null) {
                                return match.groupValues[1].toInt()
                            }
                        } else {
                            queue.add(value)
                        }
                    }
                }

                is JSONArray -> {
                    for (i in 0 until current.length()) {
                        queue.add(current.get(i))
                    }
                }
            }
        }
    } catch (e: JSONException) {
        println("Invalid JSON format: ${e.message}")
    }

    return null
}

fun sumLevels(effectList: List<String>): Int {
    val levelPattern = """Lv\.(\d+)""".toRegex() // "Lv.X" 패턴 정규식

    return effectList.sumOf { effect ->
        levelPattern.find(effect)?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }
}

fun extractGemEffectAndSkill(jsonString: String): Pair<String, String>? {
    return try {
        val jsonElement = Json.parseToJsonElement(jsonString)
        val jsonObject = jsonElement.jsonObjectSafe() ?: return null

        val effectDetails = findEffectDetails(jsonObject) ?: return null

        val skillNameRegex = Regex("<FONT COLOR='.*?'>(.*?)</FONT>")
        val effectRegex = Regex("(재사용 대기시간|피해) [\\d.]+% (증가|감소)")

        val skillName = skillNameRegex.find(effectDetails)?.groupValues?.get(1) ?: "Unknown"
        val effect = effectRegex.find(effectDetails)?.value ?: "Unknown"

        return Pair(skillName, effect)
    } catch (e: Exception) {
        Timber.e(e, "extractGemEffectAndSkill: Error parsing JSON")
        null
    }
}

fun JsonElement.jsonObjectSafe(): JsonObject? {
    return this as? JsonObject
}

fun findEffectDetails(jsonObject: JsonObject): String? {
    for ((_, value) in jsonObject) {
        if (value is JsonPrimitive && value.isString) {
            val content = value.content
            if (content.contains("재사용 대기시간") || content.contains("피해")) {
                return content
            }
        }
        if (value is JsonObject) {
            val found = findEffectDetails(value)
            if (found != null) return found
        }
    }
    return null
}




