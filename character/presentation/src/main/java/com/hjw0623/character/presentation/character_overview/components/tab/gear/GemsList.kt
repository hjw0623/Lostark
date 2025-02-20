package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.character_overview.mockup.mockGemContent
import com.hjw0623.character.presentation.character_overview.model.gem.GemsUi
import com.hjw0623.core.presentation.designsystem.LostArkAncient
import com.hjw0623.core.presentation.designsystem.LostArkLegend
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun GemsList(
    gemsList: List<GemsUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        Text(
            text = "보석",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(11) { index ->
                val gem = gemsList.getOrNull(index)

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (gem != null) {
                        AsyncImage(
                            model = gem.icon,
                            contentDescription = "보석 이미지",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = when (gem.grade) {
                                        "고대" -> LostArkAncient
                                        "유물" -> LostArkRelic
                                        "전설" -> LostArkLegend
                                        "영웅" -> LostArkPurple
                                        else -> LostArkWhite
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(color = LostArkWhite, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 2.dp, vertical = 1.dp)
                                .size(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = gem.level.toString(),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = LostArkOrange
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GemsListPreview() {
    val list = listOf(mockGemContent(), mockGemContent(), mockGemContent()) // 3개만 전달
    LostarkTheme {
        GemsList(list)
    }
}
