package com.hjw0623.data.model.collectibles

import com.hjw0623.character.domain.model.collectibles.Collectible
import com.hjw0623.character.domain.model.collectibles.CollectiblePoints

fun CollectiblePointsSerializable.toDomain(): CollectiblePoints {
    return CollectiblePoints(
        pointName = this.pointName,
        point = this.point,
        maxPoint = this.maxPoint
    )
}

fun CollectibleSerializable.toDomain(): Collectible {
    return Collectible(
        type = this.type,
        icon = this.icon,
        point = this.point,
        maxPoint = this.maxPoint,
        collectiblePoints = this.collectiblePoints.map { it.toDomain() }
    )
}