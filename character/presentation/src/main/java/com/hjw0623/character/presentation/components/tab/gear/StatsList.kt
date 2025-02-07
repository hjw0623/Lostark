package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.mockup.mockStatsContent
import com.hjw0623.character.presentation.model.profile.StatsUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostArkLightBlue
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun StatsList(
    statsUi: StatsUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "특성",
            style = Typography.bodyMedium.copy(
                color = LostArkBlack,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(4.dp)
                ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    append("공격력  ")
                    withStyle(
                        style = SpanStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(statsUi.attackPoint.toString())
                    }
                },
                style = Typography.bodyMedium.copy(
                    color = LostArkBlack
                ),
            )

            Text(
                text = buildAnnotatedString {
                    append("최대 생명력  ")
                    withStyle(
                        style = SpanStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(statsUi.hp.toString())
                    }
                },
                style = Typography.bodyMedium.copy(
                    color = LostArkBlack
                ),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(
                    color = LostArkLightBlue,
                    shape = RoundedCornerShape(4.dp)
                ),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "치명",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.critical.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "특화",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.specialty.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "신속",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.agility.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "제압",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.subdue.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "인내",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.endurance.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "숙련",
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = statsUi.proficiency.toString(),
                        style = Typography.bodyMedium.copy(
                            color = LostArkBlack,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun StatsListPreview() {
    LostarkTheme {
        StatsList(
            mockStatsContent()
        )
    }
}