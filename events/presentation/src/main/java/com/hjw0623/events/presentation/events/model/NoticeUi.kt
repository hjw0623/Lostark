package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.Notice

data class NoticeUi(
    val type: String,
    val title: String,
    val link: String
)

fun Notice.toNoticeUi(): NoticeUi {
    return NoticeUi(
        type = type,
        title = title,
        link = link
    )
}