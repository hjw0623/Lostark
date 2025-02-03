package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockBraceletContent
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.util.normalizeText
import com.hjw0623.character.presentation.util.shortSpecialEffect
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
            .padding(5.dp)
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
            AsyncImage(
                model = braceletUi.iconUri,
                contentDescription = "팔찌 이미지",
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),

            ) {
                // 스탯 텍스트 정렬
                Row {
                    braceletUi.stats.forEach {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
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

                        // `shortSpecialEffect`와 `grade`를 다른 스타일로 적용
                        val annotatedText = buildAnnotatedString {
                            append(shortSpecialEffect) // 기본 스타일 적용
                            withStyle(style = SpanStyle(color = gradeColor)) {
                                append(" ${it.grade}") // `it.grade`만 색상 변경
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