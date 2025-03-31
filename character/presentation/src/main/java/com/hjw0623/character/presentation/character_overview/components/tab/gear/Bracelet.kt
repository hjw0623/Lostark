package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.character_overview.components.GradientBackgroundItem
import com.hjw0623.character.presentation.character_overview.mockup.mockBraceletContent
import com.hjw0623.character.presentation.character_overview.model.gear.BraceletUi
import com.hjw0623.character.presentation.character_overview.util.shortSpecialEffect
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun BraceletItem(
    braceletUi: BraceletUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = braceletUi.type,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            GradientBackgroundItem(
                icon = braceletUi.iconUri,
                color1 = when (braceletUi.grade) {
                    "고대" -> 0xFF3D3325
                    "유물" -> 0xFF341A09
                    else -> 0xFFFAFAFA
                },
                color2 = when (braceletUi.grade) {
                    "고대" -> 0xFFDCC999
                    "유물" -> 0xFFA24006
                    else -> 0xFFFAFAFA
                },
                bottomStart = 8,
                bottomEnd = 8
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Column {
                    braceletUi.stats.forEach { stats ->
                        Text(
                            text = stats,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.Black,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                        )

                    }

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    braceletUi.specialEffect.forEach {
                        val shortSpecialEffect = shortSpecialEffect(
                            specialEffect = it.effect,
                            tier = braceletUi.tier,
                            grade = braceletUi.grade
                        )
                        val badgeColor = when (it.grade) {
                            "상" -> LostArkOrange
                            "중" -> LostArkBlue
                            "하" -> LostArkGreen
                            else -> LostArkDarkRed
                        }

                        Box(
                            modifier = Modifier
                                .background(color = badgeColor.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$shortSpecialEffect ${it.grade}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = badgeColor,
                                maxLines = 1
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
fun BraceletItemPreview() {
    LostarkTheme {
        BraceletItem(mockBraceletContent())
    }
}