package com.hjw0623.character.presentation.character_overview.model.collectibles

import com.hjw0623.character.domain.model.collectibles.Collectible
import com.hjw0623.character.domain.model.collectibles.CollectiblePoints
import com.hjw0623.character.presentation.R

fun Collectible.toCollectibleSummationUi(): CollectibleSummationUi {
    val icon = when(this.type) {
        "크림스네일의 해도" -> R.drawable.chart
        "위대한 미술품" -> R.drawable.masterpiece
        "모코코 씨앗" -> R.drawable.mokoko_seed
        "거인의 심장" -> R.drawable.giants_heart
        "섬의 마음" -> R.drawable.islands_heart
        "이그네아의 징표" -> R.drawable.mark_of_ignea
        "기억의 오르골" -> R.drawable.orgel_of_memory
        "오르페우스의 별" -> R.drawable.star_of_orpheus
        "항해 모험물" -> R.drawable.voyage_wonder
        "세계수의 잎" -> R.drawable.world_tree_leaf
        else -> R.drawable.error_icon
    }

    val progress = (this.point.toFloat() / this.maxPoint).coerceIn(0f, 1f)

    return CollectibleSummationUi(
        icon = icon,
        title = this.type,
        progress = progress,
        current = this.point,
        total = this.maxPoint,
        collectibleDetailList = this.collectiblePoints.map { it.toCollectibleDetail() },
    )
}

fun CollectiblePoints.toCollectibleDetail(): CollectibleDetailUi {
    val obtained = when {
        point == maxPoint -> true
        else -> false
    }
    return CollectibleDetailUi(
        name = this.pointName,
        point = this.point,
        maxPoint = this.maxPoint,
        obtained = obtained
    )
}