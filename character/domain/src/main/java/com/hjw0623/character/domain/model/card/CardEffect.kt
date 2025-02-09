package com.hjw0623.character.domain.model.card

data class CardEffect(
    val index: Int,
    val cardSlots: List<Int>,
    val items: List<CardEffectItem>
)
