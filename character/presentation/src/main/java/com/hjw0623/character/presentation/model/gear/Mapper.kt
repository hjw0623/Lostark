package com.hjw0623.character.presentation.model.gear

import com.hjw0623.character.domain.model.gear.Gear
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

    return GearUi(
        iconUri = this.icon,
        quality = quality.toString().toInt(),
        grade = this.grade,
        transcendence = transcendencePair.second,
        transcendenceGrade = transcendencePair.first,
        advancedUpgradeStep = extractFirstNumber(advancedUpgradeStep) ?: 0,
        equipmentName = this.name,
        type = this.type,
        elixirList = elixirList
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
    val result = mutableListOf<String>()

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
                            result.add(value)
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

    return result.first()
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



