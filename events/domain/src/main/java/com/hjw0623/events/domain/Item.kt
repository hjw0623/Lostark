package com.hjw0623.events.domain

data class Item(
    val name: String,
    val icon: String,
    val grade: String,
    val startTimes: List<String>?
)
