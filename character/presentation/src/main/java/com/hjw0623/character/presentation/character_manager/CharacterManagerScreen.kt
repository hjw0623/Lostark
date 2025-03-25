package com.hjw0623.character.presentation.character_manager

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.ResolvedTextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hjw0623.character.presentation.R
import com.hjw0623.character.presentation.character_manager.components.CharacterDeleteDialog
import com.hjw0623.character.presentation.character_manager.components.CharacterProgressListItem
import com.hjw0623.character.presentation.character_manager.mockup.mockCharacterProgressContent
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
fun CharacterManagerScreenRoot(
    onCharacterSettingClick: (String) -> Unit,
    onCharacterAddClick: () -> Unit,
    viewModel: CharacterManagerViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterManagerEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()

            is CharacterManagerEvent.OnDeleteCharacter -> Toast.makeText(
                context,
                event.characterName + "가 삭제되었습니다.",
                Toast.LENGTH_LONG
            ).show()

            is CharacterManagerEvent.NavigateToCharacterSetting -> {
                onCharacterSettingClick(event.characterName)
            }

            CharacterManagerEvent.NavigateToCharacterAdd -> {
                onCharacterAddClick()
            }
        }
    }

    CharacterManagerScreen(
        state = state,
        onAction = { action ->
            when (action) {
                CharacterManagerAction.OnCharacterAddClick -> onCharacterAddClick()
                is CharacterManagerAction.OnCharacterSettingClick -> onCharacterSettingClick(action.characterName)
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun CharacterManagerScreen(
    modifier: Modifier = Modifier,
    state: CharacterManagerState,
    onAction: (CharacterManagerAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(CharacterManagerAction.OnCharacterAddClick) },
                containerColor = LostArkBlue
            ) {
                Text(
                    text = "+",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        val adjustedPadding = PaddingValues(
            start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
            bottom = 0.dp
        )
        if (state.isCharacterProfileLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(adjustedPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "주간 획득 골드",
                        style = Typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.gold),
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = state.totalGold.toString(),
                            style = Typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .padding(4.dp)
                    ) {
                        Canvas(modifier = Modifier.matchParentSize()) {
                            val trackHeight = size.height
                            val progressFraction = (state.raidProgress / 100).coerceIn(0f, 1f)
                            val progressWidth = size.width * progressFraction
                            val strokeThickness = 12.dp.toPx()

                            drawLine(
                                color = LostArkGray,
                                start = Offset(0f, trackHeight / 2),
                                end = Offset(size.width, trackHeight / 2),
                                strokeWidth = strokeThickness,
                                cap = StrokeCap.Butt
                            )

                            drawLine(
                                color = LostArkBlue,
                                start = Offset(0f, trackHeight / 2),
                                end = Offset(progressWidth, trackHeight / 2),
                                strokeWidth = strokeThickness,
                                cap = StrokeCap.Butt
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.gold),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    val progress = state.totalEarnedGold / state.totalGold.toFloat() * 100

                    Text(
                        text = "획득 골드 (${progress.roundToInt()}%)",
                        style = Typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = state.totalEarnedGold.toString(),
                        style = Typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.gold),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    val remainGold = state.totalGold - state.totalEarnedGold
                    val progress = remainGold / state.totalGold.toFloat() * 100

                    Text(
                        text = "남은 골드 (${progress.roundToInt()}%)",
                        style = Typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = remainGold.toString(),
                        style = Typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }


                Spacer(modifier = Modifier.height(5.dp))

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    state.savedCharacterProgressListByServer.forEach { (server, characterList) ->
                        item {
                            Text(
                                text = server,
                                style = Typography.headlineMedium.copy(
                                    color = LostArkBlack,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }

                        items(characterList.sortedByDescending {
                            it.avgItemLevel.toDoubleOrNull() ?: 0.0
                        }) { character ->
                            CharacterProgressListItem(
                                characterProgress = character,
                                onCharacterClick = {
                                    onAction(CharacterManagerAction.OnCharacterClick(character.name))
                                },
                                onCharacterSettingClick = {
                                    onAction(
                                        CharacterManagerAction.OnCharacterSettingClick(
                                            character.name
                                        )
                                    )
                                },
                                onCharacterDeleteClick = {
                                    onAction(CharacterManagerAction.OnShowDialog(character.name))
                                },
                                onGateToggled = { characterName, raidName, gateIndex ->
                                    onAction(
                                        CharacterManagerAction.OnGateToggled(
                                            characterName,
                                            raidName,
                                            gateIndex
                                        )
                                    )
                                }
                            )

                        }
                    }
                }

            }
        }
    }
    if (state.showDialog && state.savedCharacterProgressListByServer.isNotEmpty()) {
        CharacterDeleteDialog(
            characterName = state.characterToDelete,
            onConfirm = { onAction(CharacterManagerAction.OnCharacterDeleteClick(state.characterToDelete)) },
            onDismiss = { onAction(CharacterManagerAction.OnDismissDeleteClick) }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CharacterManagerScreenPreview() {
    LostarkTheme {
        CharacterManagerScreen(
            state = CharacterManagerState(
                raidProgress = 70.0f,
                totalGold = 96700,
                totalEarnedGold = 86900,
                savedCharacterProgressListByServer = mapOf(
                    "서버1" to listOf(
                        mockCharacterProgressContent(),
                        mockCharacterProgressContent(),
                        mockCharacterProgressContent()
                    ),
                    "서버2" to listOf(
                        mockCharacterProgressContent(),
                        mockCharacterProgressContent(),
                        mockCharacterProgressContent()
                    )
                ),
            ),
            onAction = { },
        )
    }
}

