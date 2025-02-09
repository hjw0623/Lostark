package com.hjw0623.character.domain.model.engravings

data class Engravings(
    val engravings: List<Engraving>?,
    val engravingEffects: List<EngravingEffect>?,
    val arkPassiveEffect: List<ArkPassiveEffect>?
)
