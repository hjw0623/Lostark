package com.hjw0623.character.domain.model.gems

data class Skill(
    val description: List<String>,
    val gemSlot: Int,
    val icon: String,
    val name: String,
    val option: String,
    val tooltip: String
)
