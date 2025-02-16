package com.hjw0623.data.model.siblings

import com.hjw0623.character.domain.model.siblings.Sibling

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