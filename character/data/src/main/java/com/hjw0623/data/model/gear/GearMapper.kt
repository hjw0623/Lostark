package com.hjw0623.data.model.gear

import com.hjw0623.character.domain.model.gear.Gear

fun GearSerializable.toDomain(): Gear {
    return Gear(
        type = this.type,
        name = this.name,
        icon = this.icon,
        grade = this.grade,
        tooltip = this.tooltip
    )
}
