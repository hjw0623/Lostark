package com.hjw0623.character.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.mockup.mockCharacterProfileContent
import com.hjw0623.character.presentation.model.profile.CharacterProfileUi
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CharacterSummationText(
    text: String,
    textSize: Int,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        fontSize = textSize.sp,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = textAlign
    )
}

@Composable
fun CharacterSummationTextGrid(
    characterProfileUi: CharacterProfileUi
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center-align text in each column
        ) {
            CharacterSummationText(
                text = "원정대",
                textSize = 13,
                textAlign = TextAlign.Center
            )
            CharacterSummationText(
                text = "Lv.${characterProfileUi.expeditionLevel}",
                textSize = 13,
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center-align text in each column
        ) {
            CharacterSummationText(
                text = "영지",
                textSize = 13,
                textAlign = TextAlign.Center
            )
            CharacterSummationText(
                text = "Lv.${characterProfileUi.townLevel} ${characterProfileUi.townName}",
                textSize = 13,
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center-align text in each column
        ) {
            CharacterSummationText(
                text = "PvP",
                textSize = 13,
                textAlign = TextAlign.Center
            )
            CharacterSummationText(
                text = characterProfileUi.pvpGradeName,
                textSize = 13,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview
@Composable
private fun CharacterSummationTextPreview() {
    LostarkTheme {
        CharacterSummationTextGrid(
            mockCharacterProfileContent()
        )
    }
}
