package com.hjw0623.data.model.avatar

import com.hjw0623.character.domain.model.avatar.Avatar

fun AvatarSerializable.toDomain(): Avatar {
    return Avatar(
        type = this.type,
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        isSet = this.isSet,
        isInner = this.isInner,
        tooltip = this.tooltip
    )
}