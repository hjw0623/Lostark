package com.hjw0623.character.presentation.character_overview.components.tab.collectibles

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.R
import com.hjw0623.character.presentation.character_overview.mockup.mockCollectiblePointsContent
import com.hjw0623.character.presentation.character_overview.model.collectibles.CollectibleSummationUi
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CollectibleSummationListItem(
    collectibleSummation: CollectibleSummationUi
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2ECFA)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = collectibleSummation.icon),
                contentDescription = "Collectible Icon",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = collectibleSummation.title,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .padding(vertical = 4.dp)
                ) {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val trackHeight = size.height
                        val progressWidth = size.width * collectibleSummation.progress

                        drawLine(
                            color = LostArkGray,
                            start = Offset(0f, trackHeight / 2),
                            end = Offset(size.width, trackHeight / 2),
                            strokeWidth = trackHeight,
                            cap = StrokeCap.Butt
                        )

                        drawLine(
                            color = LostArkBlue,
                            start = Offset(0f, trackHeight / 2),
                            end = Offset(progressWidth, trackHeight / 2),
                            strokeWidth = trackHeight,
                            cap = StrokeCap.Butt
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${(collectibleSummation.progress * 100).toInt()}%",
                        style = TextStyle(fontSize = 12.sp, color = Color.Black)
                    )
                    Text(
                        text = "${collectibleSummation.current}/${collectibleSummation.total}",
                        style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CollectibleSummationListItemPreview() {
    val list = listOf(
        mockCollectiblePointsContent(),
        mockCollectiblePointsContent(),
        mockCollectiblePointsContent(),
    )
    LostarkTheme {
        CollectibleSummationListItem(
            collectibleSummation = CollectibleSummationUi(
                icon = R.drawable.islands_heart,
                title = "섬의 마음",
                progress = 0.709f,
                current = 73,
                total = 103,
                collectibleDetailList = list,
            )
        )
    }
}