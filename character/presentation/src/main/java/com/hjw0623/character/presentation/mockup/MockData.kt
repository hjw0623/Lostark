package com.hjw0623.character.presentation.mockup

import com.hjw0623.character.presentation.model.arkpassive.ArkPassiveUi
import com.hjw0623.character.presentation.model.arkpassive.Effect
import com.hjw0623.character.presentation.model.avatar.AvatarUi
import com.hjw0623.character.presentation.model.card.CardEffectUi
import com.hjw0623.character.presentation.model.card.CardUi
import com.hjw0623.character.presentation.model.engraving.EngravingUi
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.model.gem.GemsUi
import com.hjw0623.character.presentation.model.gear.SpecialEffect
import com.hjw0623.character.presentation.model.gear.TranscendenceUi
import com.hjw0623.character.presentation.model.profile.StatsUi
import com.hjw0623.character.presentation.model.skill.RuneUi
import com.hjw0623.character.presentation.model.skill.SkillInfoUi
import com.hjw0623.character.presentation.model.skill.SkillUi
import com.hjw0623.character.presentation.model.skill.TripodUi
import com.hjw0623.character.presentation.util.shortCardEffect


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
fun mockGemContent(): GemsUi {
    return GemsUi(
        name = "10레벨 겁화의 보석",
        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_12_105.png",
        level = 10,
        grade = "고대",
        skillName = "피니쉬 스트라이크",
        effect = "피해 44.00% 증가"
    )
}
fun mockStatsContent(): StatsUi {
    return StatsUi(
        attackPoint = 120671,
        hp = 278559,
        critical = 662,
        specialty = 1809,
        agility = 72,
        subdue = 75,
        endurance = 69,
        proficiency = 71
    )
}
fun mockEngravingContent(): EngravingUi {
    return EngravingUi(
        icon = "https://cdn-lostark.game.onstove.com/EFUI_IconAtlas/Buff/Buff_83.png",
        level = 4,
        name = "쉴드관통",
        grade = "유물",
        abilityStoneLevel = 2
    )
}
fun mockCardContent(): CardUi {
    return CardUi(
        cardIcon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/card_legend/card_legend_01_0.png",
        cardName = "웨이",
        cardLevel = 5,
        cardGrade = "전설"
    )
}
fun mockCardEffectContent(): CardEffectUi {
    return CardEffectUi(
        index = 0,
        effectName = shortCardEffect("살아서 다시 보길 바란다"),
        effectGrade = 30,
        effectDescriptions = mapOf(
            "2세트" to "뇌속성 피해 감소 +10.00%",
            "2세트" to "뇌속성 피해 감소 +10.00%",
            "2세트" to "뇌속성 피해 감소 +10.00%"
        )
    )
}
fun mockArkPassiveContent(): ArkPassiveUi {
    return ArkPassiveUi(
        evolutionPoint = 120,
        enlightenmentPoint = 101,
        leapPoint = 70,
        evolutionEffectList = listOf(
            mockArkPassiveEffectContent("진화"),
            mockArkPassiveEffectContent("진화"),
            mockArkPassiveEffectContent("진화"),
            mockArkPassiveEffectContent("진화")
        ),
        enlightenmentEffectList = listOf(
            mockArkPassiveEffectContent("깨달음"),
            mockArkPassiveEffectContent("깨달음"),
            mockArkPassiveEffectContent("깨달음"),
            mockArkPassiveEffectContent("깨달음")
        ),
        leapEffectList = listOf(
            mockArkPassiveEffectContent("도약"),
            mockArkPassiveEffectContent("도약"),
            mockArkPassiveEffectContent("도약"),
            mockArkPassiveEffectContent("도약")
        )
    )
}
fun mockArkPassiveEffectContent(type: String): Effect {
    return Effect(
        type = type,
        tier = "1티어",
        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/ark_passive_bd/ark_passive_bd_1.png",
        effect = "특화",
        level = "10",
        description = "특화가 500 증가합니다."
    )
}
fun mockSkillContent(): SkillUi {
    return SkillUi(
        name = "피니쉬 스트라이크",
        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/bk_skill/bk_skill_01_14.png",
        rune = RuneUi(
            name = "질풍",
            icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_7_194.png",
            grade = "전설",
            description = "스킬 시전 속도가 14% 증가"
        ),
        skillLevel = 14,
        firstTripod = TripodUi(
            name = "강화된 일격",
            icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/tripod_tier/tripod_tier_1_0.png",
            level = "5",
            description = "적에게 주는 피해가 32.0% 증가한다"
        ),
        secondTripod = TripodUi(
            name = "약점 포착",
            icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/tripod_tier/tripod_tier_2_33.png",
            level = "5",
            description = "피격이상 면역인 적에게 주는 피해가 70.0% 증가한다."
        ),
        thirdTripod = TripodUi(
            name = "확인사살",
            icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/tripod_tier/tripod_tier_3_40.png",
            level = "5",
            description = "콤보 조작으로 변경된다. 2회 공격은 적에게 205.5% 피해를 주지만 재사용 대기시간이 6.0초 증가한다."
        ),
        skillInfo = SkillInfoUi(
            name = "피니쉬 스트라이크",
            skillType = "[일반 스킬]",
            castingType = "일반",
            cooldownTime = "재사용 대기시간 18초",
            mana = "마나 441 소모",
            description = "대검을 강하게 내리찍어 2,529,887의 피해를 준다.",
            neutralize = "상",
            partBreak = "레벨 2",
            attackType = "백 어택",
            superArmor = "경직 면역"
        )
    )
}
fun mockAvatarContent(type: String = "무기 아바타", isInner: Boolean = true): AvatarUi {
    return AvatarUi(
        type = type,
        name = "유연한 영원 헤비 건틀릿",
        icon = "https://cdn-lostark.game.onstove.com/efui_iconatlas/shop_icon/shop_icon_7910.png",
        grade = "전설",
        isInner = isInner
    )
}


internal val emptyArkPassiveUi = ArkPassiveUi(
    evolutionPoint = 0,
    enlightenmentPoint = 0,
    leapPoint = 0,
    evolutionEffectList = emptyList(),
    enlightenmentEffectList = emptyList(),
    leapEffectList = emptyList()
)

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