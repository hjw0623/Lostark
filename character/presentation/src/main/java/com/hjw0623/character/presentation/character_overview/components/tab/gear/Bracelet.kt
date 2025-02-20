package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
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
                    .fillMaxWidth()
                    .padding(start = 5.dp),

            ) {

                Column {
                    braceletUi.stats.chunked(2).forEach { rowStats ->
                        Row {
                            rowStats.forEach {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                )
                            }
                        }
                    }
                }
                Row {
                    braceletUi.specialEffect.forEach {
                        val shortSpecialEffect = shortSpecialEffect(
                            specialEffect = it.effect,
                            tier = braceletUi.tier,
                            grade = braceletUi.grade
                        )

                        // 텍스트 스타일 정의
                        val textStyle = MaterialTheme.typography.bodySmall
                            .copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold)

                        // 등급별 색상
                        val gradeColor = when (it.grade) {
                            "하" -> LostArkGreen
                            "중" -> LostArkBlue
                            "상" -> LostArkOrange
                            else -> LostArkDarkRed
                        }

                        val annotatedText = buildAnnotatedString {
                            append(shortSpecialEffect)
                            withStyle(style = SpanStyle(color = gradeColor)) {
                                append(" ${it.grade}")
                            }
                        }

                        Text(
                            text = annotatedText,
                            style = textStyle,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(5.dp)
                        )
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