package com.hjw0623.events.data.notices

import com.hjw0623.events.data.notices.model.NoticeSerializable
import com.hjw0623.events.domain.model.Notice

fun NoticeSerializable.toDomain(): Notice {
    return Notice(
        data = this.date,
        link = this.link,
        title = this.title,
        type = this.type
    )
}