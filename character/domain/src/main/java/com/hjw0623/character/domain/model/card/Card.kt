package com.hjw0623.character.domain.model.card

data class Card(
    val slot: Int,
    val name: String,
    val icon: String,
    val awakeCount: Int,
    val awakeTotal: Int,
    val grade: String,
    val tooltip: String
)
