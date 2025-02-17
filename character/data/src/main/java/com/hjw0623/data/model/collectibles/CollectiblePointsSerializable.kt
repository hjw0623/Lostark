package com.hjw0623.data.model.collectibles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectiblePointsSerializable(
    @SerialName ("PointName") val pointName: String,
    @SerialName ("Point") val point: Int,
    @SerialName ("MaxPoint") val maxPoint: Int
)
