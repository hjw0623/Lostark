package com.hjw0623.character.presentation.character_overview.model.collectibles

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectibleDetailUi(
    val name: String,
    val point: Int,
    val maxPoint: Int,
    val obtained: Boolean,
): Parcelable
