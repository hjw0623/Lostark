package com.hjw0623.character.presentation.character_overview.model.avatar

import com.hjw0623.character.domain.model.avatar.Avatar

fun Avatar.toAvatarUi(): AvatarUi {
    val type = if (this.isInner) {
        this.type + " 덧입기"
    } else {
        this.type
    }
    return AvatarUi(
        type = type,
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        isInner = this.isInner
    )
}