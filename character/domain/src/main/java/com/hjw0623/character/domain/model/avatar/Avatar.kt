package com.hjw0623.character.domain.model.avatar

data class Avatar(
    val type: String,
    val name: String,
    val icon: String,
    val grade: String,
    val isSet: Boolean,
    val isInner: Boolean,
    val tooltip: String,
)
