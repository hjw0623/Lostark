package com.hjw0623.character.presentation.character_overview.model.gear

data class AccessoriesUi(
    val iconUri: String,
    val quality: Int,
    val grade: String?,
    val tier: Int,
    val enlightenment: String,
    val name: String,
    val type: String,
    val polishingEffectList: List<String>? = emptyList(),
)
