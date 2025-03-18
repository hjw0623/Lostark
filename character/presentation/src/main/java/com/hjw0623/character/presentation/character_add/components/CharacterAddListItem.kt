package com.hjw0623.character.presentation.character_add.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.character_add.model.CharacterAddUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography

@Composable
fun CharacterAddListItem(
    modifier: Modifier = Modifier,
    character: CharacterAddUi,
    onClick: (CharacterAddUi) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(
                    character
                )
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = LostArkWhite),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.classIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${character.itemAvgLevel} ${character.characterClassName}",
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = character.characterName,
                    style = Typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = LostArkBlack,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClick(character) }
            )
        }
    }
}

@Preview
@Composable
private fun CharacterAddListItemPreview() {
    LostarkTheme {
        CharacterAddListItem(
            character = CharacterAddUi(
                classIcon = "",
                serverName = "카단",
                characterName = "테스트 캐릭터",
                characterClassName = "창술사",
                itemAvgLevel = "1,664.7",
            ),
            onClick = { }
        )
    }
}
