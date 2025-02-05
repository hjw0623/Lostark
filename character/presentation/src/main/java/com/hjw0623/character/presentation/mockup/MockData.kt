package com.hjw0623.character.presentation.mockup

import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.model.gear.SpecialEffect
import com.hjw0623.character.presentation.model.gear.TranscendenceUi


fun mockCharacterProfileContent(): CharacterProfileUi {
    return CharacterProfileUi(
        characterImage = "https://img.lostark.co.kr/armory/9/99C69CA511035B18D094855C27DF178F303BF6543382F804E527F29B78317F23.jpg?v=20241010145630",
        characterClassName = "창술사",
        serverName = "카단",
        title = "샤샤의",
        characterName = "택티컬맘마통",
        itemAvgLevel = "1,644.17",
        characterLevel = 70,
        guildName = "이러시면곤란해요",
        expeditionLevel = 253,
        pvpGradeName = "20급",
        townLevel = 70,
        townName = "택티컬"
    )
}
fun mockEquipmentContent(): GearUi {
    return GearUi(
        iconUri = "https://cdn-lostark.game.onstove.com/efui_iconatlas/smw_item/smw_item_136.png",
        quality = 30,
        grade = "유물",
        transcendence = 21,
        transcendenceGrade = 7,
        advancedUpgradeStep = 10,
        type = "무기",
        equipmentName = "운명의 결단 창",
        elixirList = listOf("회피의 달인 Lv2", "회심(혼돈) Lv5"),
        elixirSum = 50
    )
}
fun mockJewelryContent(): AccessoriesUi {
    return AccessoriesUi(
        iconUri = "https://cdn-lostark.game.onstove.com/efui_iconatlas/acc/acc_215.png",
        quality = 30,
        grade = "고대",
        tier = 3,
        enlightenment = "13",
        name = "도래한 결전의 목걸이",
        type = "목걸이",
        polishingEffectList = listOf("적에게 주는 피해 +1.20%", "추가 피해 +0.70%", "상태이상 공격 지속시간 +1.00%")
    )
}
fun mockAbilityStoneContent(): AbilityStoneUi {
    return AbilityStoneUi(
        type = "어빌리티 스톤",
        name = "위대한 비상의 돌",
        iconUri = "https://cdn-lostark.game.onstove.com/efui_iconatlas/ability/ability_246.png",
        grade = "고대",
        hp = 23481,
        bonusHp = 3525,
        engravingList = listOf("[원한] Lv.1", "[아드레날린] Lv.4", "[이동속도 감소] Lv.0"),
        levelBonus = "기본 공격력 +1.50%"
    )
}
fun mockBraceletContent(): BraceletUi {
    return BraceletUi(
        type = "팔찌",
        name = "찬란한 구원자의 팔찌",
        iconUri = "https://cdn-lostark.game.onstove.com/efui_iconatlas/acc/acc_327.png",
        grade = "고대",
        tier = 4,
        stats = listOf("신속 +100", "치명 +115"),
        specialEffect = listOf(
            SpecialEffect(
            "공격 적중 시 매 초마다 10초 동안 무기 공격력 +1160, 공격 및 이동 속도 +1% 증가한다.(최대 6중첩)","하",
                ),
            SpecialEffect(
            "공격 적중 시 매 초마다 10초 동안 무기 공격력 +1160, 공격 및 이동 속도 +1% 증가한다.(최대 6중첩)","하",
                ),
            SpecialEffect(
    "공격 적중 시 매 초마다 10초 동안 무기 공격력 +1160, 공격 및 이동 속도 +1% 증가한다.(최대 6중첩)","하",
                )
        )
    )
}
fun mockElixirContent(): ElixirUi {
    return ElixirUi(

        total = 50,
        activeEffect = "회심 2단계"
    )
}
fun mockTranscendenceUi(): TranscendenceUi {
    return TranscendenceUi(
        total = 111,
        avgLevel = 6.8
    )
}
internal val emptyBraceletUi = BraceletUi(
    type = "",
    name = "",
    iconUri = "",
    grade = "",
    tier = 0,
    stats = emptyList(),
    specialEffect = emptyList()
)
internal val emptyAbilityStoneUi = AbilityStoneUi(
    type = "",
    name = "",
    iconUri = "",
    grade = "",
    hp = 0,
    bonusHp = 0,
    engravingList = emptyList(),
    levelBonus = ""
)