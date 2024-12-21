package com.hjw0623.core.data.networking.events.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RewardItemSerializable(
    @SerialName("ItemLevel") val itemLevel: Int,
    @SerialName("Items") val items: List<ItemSerializable> = emptyList()
)