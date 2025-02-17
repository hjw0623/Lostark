package com.hjw0623.data.model.collectibles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectibleSerializable(
    @SerialName ("Type") val type: String,
    @SerialName ("Icon") val icon: String,
    @SerialName ("Point") val point: Int,
    @SerialName ("MaxPoint") val maxPoint: Int,
    @SerialName ("CollectiblePoints") val collectiblePoints: List<CollectiblePointsSerializable>
)