package com.hjw0623.character.presentation

import androidx.compose.runtime.Immutable
import com.hjw0623.character.presentation.mockup.emptyAbilityStoneUi
import com.hjw0623.character.presentation.mockup.emptyBraceletUi
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.gear.TranscendenceUi
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi

@Immutable
data class CharacterState(
    val isCharacterProfileLoading: Boolean = false,
    val isGearLoading: Boolean = false,
    val searchedCharacterName: String = "드래디어",
    val characterProfile: CharacterProfileUi? = null,
    val gearList: List<GearUi> = emptyList(),
    val accessoriesList: List<AccessoriesUi> = emptyList(),
    val abilityStone: AbilityStoneUi = emptyAbilityStoneUi,
    val bracelet: BraceletUi = emptyBraceletUi,
    val elixir: ElixirUi = ElixirUi(),
    val transcendence: TranscendenceUi = TranscendenceUi()
) {
    val isLoading: Boolean
        get() = isCharacterProfileLoading || isGearLoading
}