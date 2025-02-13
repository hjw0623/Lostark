package com.hjw0623.character.domain.model.skill

data class Skill(
    val name: String,
    val icon: String,
    val level: Int,
    val type: String,
    val skillType: Int,
    val tripods: List<Tripod>,
    val rune: Rune?,
    val tooltip: String
)
