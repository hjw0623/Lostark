package com.hjw0623.character.presentation.character_overview.components.tab.arkpassive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.character_overview.mockup.mockArkPassiveContent
import com.hjw0623.character.presentation.character_overview.model.arkpassive.ArkPassiveUi
import com.hjw0623.core.presentation.designsystem.LostArkEnlightenment
import com.hjw0623.core.presentation.designsystem.LostArkEvolution
import com.hjw0623.core.presentation.designsystem.LostArkLeap
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun ArkPassiveScreen(
    arkPassive: ArkPassiveUi
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(12.dp),
            )
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = " 진화 ",
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = LostArkWhite
                ),
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = LostArkEvolution,
                            cornerRadius = CornerRadius(5.dp.toPx())
                        )
                    }
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = arkPassive.evolutionPoint.toString(),
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = LostArkEvolution
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            ArkPassiveListItem(arkPassive.evolutionEffectList)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = " 깨달음 ",
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = LostArkWhite
                ),
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = LostArkEnlightenment,
                            cornerRadius = CornerRadius(5.dp.toPx())
                        )
                    }
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = arkPassive.enlightenmentPoint.toString(),
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = LostArkEnlightenment
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            ArkPassiveListItem(arkPassive.enlightenmentEffectList)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = " 도약 ",
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = LostArkWhite
                ),
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = LostArkLeap,
                            cornerRadius = CornerRadius(5.dp.toPx())
                        )
                    }
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = arkPassive.leapPoint.toString(),
                style = Typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color =  LostArkLeap
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            ArkPassiveListItem(arkPassive.leapEffectList)
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun ArkPassiveScreenPreview() {
    LostarkTheme {
        ArkPassiveScreen(
            arkPassive = mockArkPassiveContent()
        )
    }
}
