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
import com.hjw0623.character.presentation.mockup.mockElixirContent
import com.hjw0623.character.presentation.model.gear.BraceletUi
import com.hjw0623.character.presentation.model.gear.ElixirUi
import com.hjw0623.character.presentation.util.shortSpecialEffect
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkDarkRed
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostArkOrange
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun ElixirItem(elixirUi: ElixirUi) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = elixirUi.type,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                model = elixirUi.icon,
                contentDescription = "엘릭서 이미지",
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),

                ) {

                Text(
                    text = "합계" + elixirUi.total.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color.Black
                )
                Text(
                    text = elixirUi.activeEffect,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall
                        .copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold),
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

@Preview
@Composable
fun ElixirItemPreview() {
    LostarkTheme {
        ElixirItem(mockElixirContent())
    }
}