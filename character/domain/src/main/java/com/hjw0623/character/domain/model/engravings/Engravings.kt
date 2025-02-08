package com.hjw0623.character.domain.model.engravings

data class Engravings(
    val engravings: List<Engraving>?,
    val effects: List<Effect>?,
    val arkPassiveEffect: List<ArkPassiveEffect>?
)
