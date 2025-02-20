package com.hjw0623.character.presentation.character_overview.model.skill

data class SkillUi(
    val name: String,
    val icon: String,
    val rune: RuneUi?,
    val skillLevel: Int,
    val firstTripod: TripodUi?,
    val secondTripod: TripodUi?,
    val thirdTripod: TripodUi?,
    val skillInfo: SkillInfoUi,
)
