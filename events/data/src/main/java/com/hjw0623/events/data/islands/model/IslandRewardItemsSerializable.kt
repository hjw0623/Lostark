package com.hjw0623.events.data.islands.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IslandRewardItemsSerializable(
    @SerialName("ItemLevel") val itemLevel: Int,
    @SerialName("Items") val items: List<ItemSerializable> = emptyList()
)