package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.components.GradientBackgroundItem
import com.hjw0623.character.presentation.character_overview.mockup.mockEquipmentContent
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.core.presentation.designsystem.LostArkAncient
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkEsther
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostArkYellow
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun EquipmentListItem(gearUi: GearUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
            .background(
                color = LostArkWhite,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientBackgroundItem(
                icon = gearUi.iconUri,
                color1 = when(gearUi.grade) {
                    "고대" -> 0xFF3D3325
                    "유물" -> 0xFF341A09
                    "에스더" -> 0xFF0C2E2C
                    else -> 0xFFFAFAFA
                },
                color2 = when(gearUi.grade) {
                    "고대" -> 0xFFDCC999
                    "유물" -> 0xFFA24006
                    "에스더" -> 0xFF2FABA8
                    else -> 0xFFFAFAFA
                }
            )
            Box(
                modifier = Modifier
                    .width(45.dp)
                    .height(18.dp)
                    .background(
                        when {
                            gearUi.quality == 100 -> LostArkOrange
                            gearUi.quality > 90 -> LostArkPurple
                            gearUi.quality > 70 -> LostArkBlue
                            gearUi.quality > 30 -> LostArkGreen
                            gearUi.quality > 10 -> LostArkYellow
                            else -> LostArkDarkRed
                        },
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )

            ) {
                Text(
                    text = gearUi.quality.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = LostArkWhite
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (gearUi.transcendence != 0) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = "https://cdn-lostark.game.onstove.com/2018/obt/assets/images/common/game/ico_tooltip_transcendence.png",
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${gearUi.transcendence} ",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 13.sp
                        ),
                        color = LostArkYellow
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${gearUi.transcendenceGrade}단계",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    )
                }
            }

            if (gearUi.advancedUpgradeStep != 0) {
                Text(
                    text = "상재x${gearUi.advancedUpgradeStep} ${gearUi.equipmentName} ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    color = when (gearUi.grade) {
                        "고대" -> LostArkAncient
                        "유물" -> LostArkRelic
                        "에스더" -> LostArkEsther
                        else -> LostArkBlack
                    }
                )
            } else {
                Text(
                    text = gearUi.equipmentName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp
                    ),
                    color = when (gearUi.grade) {
                        "고대" -> LostArkAncient
                        "유물" -> LostArkRelic
                        "에스더" -> LostArkEsther
                        else -> LostArkBlack
                    }
                )
            }

            if (!gearUi.elixirList.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp)
                ) {
                    gearUi.elixirList.forEach { elixir ->
                        Text(
                            text = elixir,
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
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
fun EquipmentListItemPreview() {
    LostarkTheme {
        EquipmentListItem(mockEquipmentContent())
    }
}