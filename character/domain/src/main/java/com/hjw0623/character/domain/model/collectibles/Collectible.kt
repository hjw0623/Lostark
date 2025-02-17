package com.hjw0623.character.domain.model.collectibles

data class Collectible(
    val type: String,
    val icon: String,
    val point: Int,
    val maxPoint: Int,
    val collectiblePoints: List<CollectiblePoints>
)
