package com.hjw0623.character.presentation.components.tab.gear

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockBraceletContent
import com.hjw0623.character.presentation.model.gear.BraceletUi
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
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AsyncImage(
                model = braceletUi.iconUri,
                contentDescription = "팔찌 이미지",
                modifier = Modifier.size(40.dp).padding(5.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    braceletUi.stats.forEach {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
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