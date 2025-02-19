package com.hjw0623.character.presentation

import androidx.compose.runtime.Immutable
import com.hjw0623.character.domain.model.collectibles.Collectible
import com.hjw0623.character.presentation.mockup.emptyAbilityStoneUi
import com.hjw0623.character.presentation.mockup.emptyArkPassiveUi
import com.hjw0623.character.presentation.mockup.emptyBraceletUi
import com.hjw0623.character.presentation.model.arkpassive.ArkPassiveUi
import com.hjw0623.character.presentation.model.avatar.AvatarUi
import com.hjw0623.character.presentation.model.card.CardEffectUi
import com.hjw0623.character.presentation.model.card.CardUi
import com.hjw0623.character.presentation.model.collectibles.CollectibleSummationUi
import com.hjw0623.character.presentation.model.engraving.EngravingUi
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.gem.GemsUi
import com.hjw0623.character.presentation.model.gear.TranscendenceUi
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi
import com.hjw0623.character.presentation.model.profile.StatsUi
import com.hjw0623.character.presentation.model.siblings.SiblingUi
import com.hjw0623.character.presentation.model.skill.SkillUi

@Immutable
data class CharacterState(
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

    val searchedCharacterName: String = "택티컬맘마통",

    val characterProfile: CharacterProfileUi? = null,
    val gearList: List<GearUi> = emptyList(),
    val accessoriesList: List<AccessoriesUi> = emptyList(),
    val abilityStone: AbilityStoneUi = emptyAbilityStoneUi,
    val bracelet: BraceletUi = emptyBraceletUi,
    val elixir: ElixirUi = ElixirUi(),
    val transcendence: TranscendenceUi = TranscendenceUi(),
    val gemsList: List<GemsUi> = emptyList(),
    val stats: StatsUi = StatsUi(),
    val engraving: List<EngravingUi> = emptyList(),
    val card: List<CardUi> = emptyList(),
    val cardEffect: List<CardEffectUi> = emptyList(),
    val arkPassive: ArkPassiveUi = emptyArkPassiveUi,
    val skillList: List<SkillUi> = emptyList(),
    val avatarList: List<AvatarUi> = emptyList(),
    val siblingList: List<SiblingUi> = emptyList(),
    val collectibleSummationList: List<CollectibleSummationUi> = emptyList()
) {
    val isLoading: Boolean
        get() = isCharacterProfileLoading || isGearLoading || isGemLoading || isEngravingLoading || isCardLoading
                || isArkPassiveLoading || isSkillLoading || isAvatarLoading || isSiblingLoading || isCollectibleLoading
}