package com.hjw0623.character.presentation.character_manager.model

data class CharacterProgressUi(
    val icon: String,
    val server: String,
    val name: String,
    val className: String,
    val avgItemLevel: String,
    val totalGold: Int,
    val earnedGold: Int,
    val raids: List<RaidProgressUi>,
    val isExpanded: Boolean = false
)
