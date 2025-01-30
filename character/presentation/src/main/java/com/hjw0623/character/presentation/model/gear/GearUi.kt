package com.hjw0623.character.presentation.model.gear


data class GearUi(
    val iconUri: String,
    val quality: Int,
    val grade: String?,
    val transcendence: Int,
    val transcendenceGrade: Int,
    val advancedUpgradeStep: Int?,
    val equipmentName: String,
    val type: String,
    val elixirList: List<String>?,
)


