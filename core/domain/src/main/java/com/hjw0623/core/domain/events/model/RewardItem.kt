package com.hjw0623.core.domain.events.model


data class RewardItem(
    val itemLevel: Int,
    val items: List<Item>
)