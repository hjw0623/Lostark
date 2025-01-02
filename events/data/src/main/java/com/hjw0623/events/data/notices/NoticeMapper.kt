package com.hjw0623.events.data.notices

import com.hjw0623.events.data.notices.model.NoticeSerializable
import com.hjw0623.events.domain.Notice

fun NoticeSerializable.toDomain(): com.hjw0623.events.domain.Notice {
    return com.hjw0623.events.domain.Notice(
        data = this.date,
        link = this.link,
        title = this.title,
        type = this.type
    )
}