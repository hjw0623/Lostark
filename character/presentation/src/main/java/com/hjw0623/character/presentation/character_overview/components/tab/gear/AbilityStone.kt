package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.hjw0623.character.presentation.character_overview.components.GradientBackgroundItem
import com.hjw0623.character.presentation.character_overview.mockup.mockAbilityStoneContent
import com.hjw0623.character.presentation.character_overview.model.gear.AbilityStoneUi
import com.hjw0623.core.presentation.designsystem.LostArkAncient
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkRelic
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun AbilityStoneItem(abilityStoneUi: AbilityStoneUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientBackgroundItem(
                icon = abilityStoneUi.iconUri,
                color1 = when (abilityStoneUi.grade) {
                    "고대" -> 0xFF3D3325
                    "유물" -> 0xFF341A09
                    else -> 0xFFFAFAFA
                },
                color2 = when (abilityStoneUi.grade) {
                    "고대" -> 0xFFDCC999
                    "유물" -> 0xFFA24006
                    else -> 0xFFFAFAFA
                }
            )
            Box(
                modifier = Modifier
                    .width(45.dp)
                    .height(18.dp)
                    .background(
                        when (abilityStoneUi.grade) {
                            "고대" -> LostArkAncient
                            "유물" -> LostArkRelic
                            else -> LostArkBlack
                        },
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = abilityStoneUi.grade,
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
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(5.dp),
        ){
            abilityStoneUi.engravingList.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = LostArkBlack
                    ),
                    maxLines = 1
                )
            }
        }
    }
}


@Preview
@Composable
private fun AbilityStoneItemPreview() {
    LostarkTheme {
        AbilityStoneItem(mockAbilityStoneContent())
    }
}