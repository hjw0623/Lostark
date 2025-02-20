package com.hjw0623.character.presentation.character_overview.model.collectibles

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectibleSummationUi(
    val icon: Int,
    val title: String,
    val progress: Float,
    val current: Int,
    val total: Int,
    val collectibleDetailList: List<CollectibleDetailUi>
): Parcelable
