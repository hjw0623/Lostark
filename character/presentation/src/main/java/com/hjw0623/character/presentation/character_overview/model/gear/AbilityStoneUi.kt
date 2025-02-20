package com.hjw0623.character.presentation.character_overview.model.gear

data class AbilityStoneUi(
    val type: String,
    val name: String,
    val iconUri: String,
    val grade: String,
    val hp: Int,
    val bonusHp: Int,
    val engravingList: List<String>,
    val levelBonus: String
)
