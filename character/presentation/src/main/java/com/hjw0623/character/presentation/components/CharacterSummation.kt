package com.hjw0623.character.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.mockup.mockCharacterProfileContent
import com.hjw0623.character.presentation.model.CharacterProfileUi
import com.hjw0623.core.presentation.designsystem.LostArkDarkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme

@Composable
fun CharacterProfile(
    characterProfileUi: CharacterProfileUi,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = LostArkDarkGray)
    ) {
        AsyncImage(
            model = characterProfileUi.characterImage,
            contentDescription = "Background Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                CharacterSummationText(
                    text = "Lv${characterProfileUi.characterLevel} ${characterProfileUi.characterClassName}",
                    textSize = 13
                )
                CharacterSummationText(
                    text = "@${characterProfileUi.serverName}",
                    textSize = 13
                )
                CharacterSummationText(
                    text = characterProfileUi.characterName,
                    textSize = 20
                )
                CharacterSummationText(
                    text = "Lv ${characterProfileUi.itemAvgLevel}",
                    textSize = 16
                )
                CharacterSummationText(
                    text = characterProfileUi.guildName,
                    textSize = 16
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Align grid at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                CharacterSummationTextGrid(characterProfileUi = characterProfileUi)
            }
        }
    }
}




@Preview
@Composable
private fun CharacterProfilePreview() {
    LostarkTheme {
        CharacterProfile(
            characterProfileUi = mockCharacterProfileContent()
        )
    }
}