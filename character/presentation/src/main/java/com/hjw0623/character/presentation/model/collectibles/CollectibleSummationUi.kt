package com.hjw0623.character.presentation.model.collectibles

data class CollectibleSummationUi(
    val icon: Int,
    val title: String,
    val progress: Float,
    val current: Int,
    val total: Int,
    val collectibleDetailList: List<CollectibleDetailUi>
)

