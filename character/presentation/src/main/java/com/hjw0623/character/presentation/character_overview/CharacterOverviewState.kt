package com.hjw0623.character.presentation.character_overview

import androidx.compose.runtime.Immutable
import com.hjw0623.character.presentation.character_overview.mockup.emptyAbilityStoneUi
import com.hjw0623.character.presentation.character_overview.mockup.emptyArkPassiveUi
import com.hjw0623.character.presentation.character_overview.mockup.emptyBraceletUi
import com.hjw0623.character.presentation.character_overview.model.arkpassive.ArkPassiveUi
import com.hjw0623.character.presentation.character_overview.model.avatar.AvatarUi
import com.hjw0623.character.presentation.character_overview.model.card.CardEffectUi
import com.hjw0623.character.presentation.character_overview.model.card.CardUi
import com.hjw0623.character.presentation.character_overview.model.collectibles.CollectibleSummationUi
import com.hjw0623.character.presentation.character_overview.model.engraving.EngravingUi
import com.hjw0623.character.presentation.character_overview.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.character_overview.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.character_overview.model.gear.BraceletUi
import com.hjw0623.character.presentation.character_overview.model.gear.GearUi
import com.hjw0623.character.presentation.character_overview.model.gem.GemsUi
import com.hjw0623.character.presentation.character_overview.model.profile.CharacterProfileUi
import com.hjw0623.character.presentation.character_overview.model.profile.StatsUi
import com.hjw0623.character.presentation.character_overview.model.skill.SkillUi


@Immutable
data class CharacterOverviewState(
    val isCharacterProfileLoading: Boolean = false,
    val isGearLoading: Boolean = false,
    val isGemLoading: Boolean = false,
    val isEngravingLoading: Boolean = false,
    val isCardLoading: Boolean = false,
    val isCollectibleLoading: Boolean = false,

    val isArkPassiveLoading: Boolean = false,
    val isSkillLoading: Boolean = false,
    val isAvatarLoading: Boolean = false,
    val isSiblingLoading: Boolean = false,

    val searchedCharacterName: String = "",

    val characterProfile: CharacterProfileUi? = null,
    val gearList: List<GearUi> = emptyList(),
    val accessoriesList: List<AccessoriesUi> = emptyList(),
    val abilityStone: AbilityStoneUi = emptyAbilityStoneUi,
    val bracelet: BraceletUi = emptyBraceletUi,
    val elixir: com.hjw0623.character.presentation.character_overview.model.gear.ElixirUi = com.hjw0623.character.presentation.character_overview.model.gear.ElixirUi(),
    val transcendence: com.hjw0623.character.presentation.character_overview.model.gear.TranscendenceUi = com.hjw0623.character.presentation.character_overview.model.gear.TranscendenceUi(),
    val gemsList: List<GemsUi> = emptyList(),
    val stats: StatsUi = StatsUi(),
    val engraving: List<EngravingUi> = emptyList(),
    val card: List<CardUi> = emptyList(),
    val cardEffect: List<CardEffectUi> = emptyList(),
    val arkPassive: ArkPassiveUi = emptyArkPassiveUi,
    val skillList: List<SkillUi> = emptyList(),
    val avatarList: List<AvatarUi> = emptyList(),
    val siblingList: List<com.hjw0623.character.presentation.character_overview.model.siblings.SiblingUi> = emptyList(),
    val collectibleSummationList: List<CollectibleSummationUi> = emptyList()
) {
    val isLoading: Boolean
        get() = isCharacterProfileLoading || isGearLoading || isGemLoading || isEngravingLoading || isCardLoading
                || isArkPassiveLoading || isSkillLoading || isAvatarLoading || isSiblingLoading || isCollectibleLoading
}