package com.hjw0623.manager.data.siblings

import com.hjw0623.manager.domain.siblings.Sibling

fun SiblingSerializable.toDomain(): Sibling {
    return Sibling(
        serverName = this.serverName,
        characterName = this.characterName,
        characterLevel = this.characterLevel,
        characterClassName = this.characterClassName,
        itemAvgLevel = this.itemAvgLevel,
        itemMaxLevel = this.itemMaxLevel
    )
}