package com.hjw0623.core.domain.events.model

data class Event(
    val endDate: String,
    val link: String,
    val rewardDate: String? = "",
    val startDate: String,
    val thumbnail: String,
    val title: String
)
