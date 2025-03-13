@file:OptIn(ExperimentalFoundationApi::class)

package com.hjw0623.character.presentation.character_manager.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hjw0623.character.presentation.R
import com.hjw0623.character.presentation.character_manager.CharacterManagerAction
import com.hjw0623.character.presentation.character_manager.mockup.mockCharacterProgressContent
import com.hjw0623.character.presentation.character_manager.model.CharacterProgressUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkDarkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography


@Composable
fun CharacterProgressListItem(
    modifier: Modifier = Modifier,
    characterProgress: CharacterProgressUi,
    onCharacterClick: () -> Unit,
    onCharacterSettingClick: () -> Unit,
    onCharacterDeleteClick: () -> Unit,
    onGateToggled: (String, String, Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = { onCharacterClick() },
                onLongClick = { onCharacterDeleteClick() }
            ),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = characterProgress.icon,
                contentDescription = "클래스 이미지",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row {
                    Column {
                        Text(
                            text = characterProgress.server,
                            style = Typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = LostArkBlack
                            )
                        )
                        Text(
                            text = characterProgress.name,
                            style = Typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = LostArkBlack
                            )
                        )
                    }
                    IconButton(
                        onClick = { onCharacterSettingClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                            tint = LostArkBlack
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    GoldTextRow("총 골드", characterProgress.totalGold)
                    GoldTextRow("획득 골드", characterProgress.earnedGold)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "아이템 레벨: ${characterProgress.avgItemLevel}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = LostArkBlack,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = characterProgress.className,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = LostArkDarkGray,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
        if (characterProgress.isExpanded) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            ) {
                characterProgress.raids.forEach { raid ->
                    RaidProgressItem(
                        raid = raid,
                        onGateToggled = { gateIndex ->
                            onGateToggled(characterProgress.name, raid.raidName, gateIndex)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GoldTextRow(label: String, amount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 2.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "gold icon",
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "$label: $amount",
            style = Typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = LostArkBlack
            )
        )
    }
}

@Preview
@Composable
private fun CharacterProgressListItemPreview() {
    LostarkTheme {
        CharacterProgressListItem(
            characterProgress = mockCharacterProgressContent(),
            onCharacterSettingClick = { },
            onCharacterDeleteClick = { },
            onCharacterClick = { },
            onGateToggled = { _, _, _ -> }
        )
    }
}

