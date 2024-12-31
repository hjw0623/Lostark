package com.hjw0623.core.domain.islands.model

data class Item(
    val name: String,
    val icon: String,
    val grade: String,
    val startTimes: List<String>?
)