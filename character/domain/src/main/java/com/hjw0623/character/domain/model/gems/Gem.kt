package com.hjw0623.character.domain.model.gems

data class Gem(
    val grade: String,
    val icon: String,
    val level: Int,
    val name: String,
    val slot: Int,
    val tooltip: String
)
