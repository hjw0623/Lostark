package com.hjw0623.character.presentation.model.card

data class CardEffectUi(
    val index: Int,
    val effectName: String,
    val effectGrade: Int,
    val effectDescriptions: Map<String, String>
)
