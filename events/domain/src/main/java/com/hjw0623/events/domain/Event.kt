package com.hjw0623.events.domain

data class Event(
    val endDate: String,
    val link: String,
    val rewardDate: String? = "",
    val startDate: String,
    val thumbnail: String,
    val title: String
)

