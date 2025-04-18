package com.hjw0623.character.presentation.character_overview.components.tab.gear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.character_overview.components.GradientBackgroundItem
import com.hjw0623.character.presentation.character_overview.mockup.mockElixirContent
import com.hjw0623.character.presentation.character_overview.model.gear.ElixirUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun ElixirItem(
    elixirUi: ElixirUi,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = elixirUi.type,
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
                icon = elixirUi.icon,
                color1 = 0xFF362003,
                color2 = 0xFF9E5F04,
                bottomStart = 8,
                bottomEnd = 8
            )
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "합계 " + elixirUi.total.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = LostArkBlack,
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (elixirUi.activeEffect == "") "없음"
                        else elixirUi.activeEffect,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LostArkBlack,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ElixirItemPreview() {
    LostarkTheme {
        ElixirItem(mockElixirContent())
    }
}