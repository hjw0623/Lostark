package com.hjw0623.character.presentation.character_overview.model.arkpassive

data class ArkPassiveUi(
    val evolutionPoint: Int,
    val enlightenmentPoint: Int,
    val leapPoint: Int,
    val evolutionEffectList: List<Effect>,
    val enlightenmentEffectList: List<Effect>,
    val leapEffectList: List<Effect>
)

data class Effect(
    val type: String,
    val tier: String,
    val icon: String,
    val effect: String,
    val level: String,
    val description: String
)