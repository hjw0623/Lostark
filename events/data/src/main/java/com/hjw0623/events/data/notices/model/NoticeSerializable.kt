package com.hjw0623.events.data.notices.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoticeSerializable(
    @SerialName("Date") val date: String,
    @SerialName("Link") val link: String,
    @SerialName("Title") val title: String,
    @SerialName("Type") val type: String
)