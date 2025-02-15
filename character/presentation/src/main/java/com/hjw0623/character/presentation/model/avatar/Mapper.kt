package com.hjw0623.character.presentation.model.avatar

import com.hjw0623.character.domain.model.avatar.Avatar
import timber.log.Timber

fun Avatar.toAvatarUi(): AvatarUi {
    val type = if (this.isInner) {
        this.type + " 덧입기"
    } else {
        this.type
    }
    Timber.tag("fdfdf").d(type)
    return AvatarUi(
        type = type,
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        isInner = this.isInner
    )
}