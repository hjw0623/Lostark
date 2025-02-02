package com.hjw0623.character.presentation

import androidx.compose.runtime.Immutable
import com.hjw0623.character.presentation.model.gear.AbilityStoneUi
import com.hjw0623.character.presentation.model.gear.AccessoriesUi
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi

@Immutable
data class CharacterState(
    val isCharacterProfileLoading: Boolean = false,
    val isGearLoading: Boolean = false,
    val searchedCharacterName: String = "히후히후",
    val characterProfile: CharacterProfileUi? = null,
    val gearList: List<GearUi> = emptyList(),
    val accessoriesList: List<AccessoriesUi> = emptyList(),
    val abilityStone: List<AbilityStoneUi> = emptyList(),
    val bracelet: List<BraceletUi> = emptyList()
) {
    val isLoading: Boolean
        get() = isCharacterProfileLoading || isGearLoading
}