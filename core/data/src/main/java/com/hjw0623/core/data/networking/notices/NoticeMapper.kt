package com.hjw0623.core.data.networking.notices

import com.hjw0623.core.data.networking.notices.model.NoticeSerializable
import com.hjw0623.core.domain.notices.model.Notice

fun NoticeSerializable.toDomain(): Notice {
    return Notice(
        data = this.date,
        link = this.link,
        title = this.title,
        type = this.type
    )
}