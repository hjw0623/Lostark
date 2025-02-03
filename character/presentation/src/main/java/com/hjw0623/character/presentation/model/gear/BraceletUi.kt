package com.hjw0623.character.presentation.model.gear

data class BraceletUi(
    val type: String,
    val name: String,
    val iconUri: String,
    val grade: String,
    val tier: Int,
    val stats: List<String>,
    val specialEffect: List<SpecialEffect>
)
data class SpecialEffect(
    val effect: String,
    val grade: String
)