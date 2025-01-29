package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockEquipmentContent
import com.hjw0623.character.presentation.model.gear.GearUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostArkPurple
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostArkYellow
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import kotlin.math.round

@Composable
fun EquipmentListItem(gearUi: GearUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
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
            AsyncImage(
                model = gearUi.iconUri,
                contentDescription = "장비 이미지",
                modifier = Modifier
                    .size(40.dp)
                    .padding(3.dp),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                        shape = RoundedCornerShape(15.dp)
                    )

            ) {
                Text(
                    text = gearUi.quality.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp,
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
                    color = LostArkOrange
                )
            } else {
                Text(
                    text = gearUi.equipmentName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp
                    ),
                    color = LostArkOrange
                )
            }

            if (!gearUi.elixirList.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    gearUi.elixirList.forEach { elixir ->
                        Text(
                            text = elixir,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                            modifier = Modifier
                                .padding(5.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 5.dp)
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