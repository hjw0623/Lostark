package com.hjw0623.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GradientBackgroundItem(
    modifier: Modifier,
    icon: String,
    color1: Long,
    color2: Long,
    topStart: Int = 8,
    topEnd: Int = 8,
    bottomStart: Int = 0,
    bottomEnd: Int = 0
) {

    Box(
        modifier = modifier
            .size(45.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(color1), Color(color2)),
                    start = Offset(0f, 0f),
                    end = Offset(45f, 45f)
                ),
                shape = RoundedCornerShape(
                    topStart = topStart.dp,
                    topEnd = topEnd.dp,
                    bottomStart = bottomStart.dp,
                    bottomEnd = bottomEnd.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = icon,
            contentDescription = "img",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit
        )
    }
}