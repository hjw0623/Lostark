package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.character_overview.mockup.mockEngravingContent
import com.hjw0623.character.presentation.character_overview.model.engraving.EngravingUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkLegend
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun EngravingsList(
    engravingUiList: List<EngravingUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "각인",
            style = Typography.bodyMedium.copy(
                color = LostArkBlack,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            engravingUiList.forEach { engraving ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = engraving.icon,
                        contentDescription = "각인 이미지",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        "Lv."+engraving.level,
                        color = LostArkWhite,
                        style = Typography.bodyMedium.copy(
                            fontSize = 10.sp
                        ),
                        modifier = Modifier
                            .drawBehind {
                                drawRoundRect(
                                    when(engraving.grade) {
                                        "유물" -> LostArkRelic
                                        "전설" -> LostArkLegend
                                        "영웅" -> LostArkPurple
                                        else -> LostArkBlue
                                    },
                                    cornerRadius = CornerRadius(10.dp.toPx())
                                )
                            }
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Column {
                        Text(
                            text = engraving.name,
                            style = Typography.bodyMedium.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            maxLines = 1,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                        if (engraving.abilityStoneLevel != 0) {
                            Text(
                                text = "x" + engraving.abilityStoneLevel + " 어빌리티 스톤",
                                style = Typography.bodyMedium.copy(
                                    fontSize = 10.sp,
                                    color = LostArkBlue,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(8.dp)
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
private fun EngravingListPreview() {
    val list = listOf(
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent(),
        mockEngravingContent()
    )
    LostarkTheme {
        EngravingsList(
            list
        )
    }
}