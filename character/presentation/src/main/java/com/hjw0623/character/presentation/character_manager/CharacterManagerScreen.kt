package com.hjw0623.character.presentation.character_manager

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hjw0623.character.presentation.R
import com.hjw0623.character.presentation.character_manager.components.CharacterProgressListItem
import com.hjw0623.character.presentation.character_manager.mockup.mockCharacterProgressContent
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkBlue
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterManagerScreenRoot(
    onCharacterDeleteClick: () -> Unit,
    onCharacterSettingClick: () -> Unit,
    onCharacterAddClick: () -> Unit,
    viewModel: ManagerViewModel = koinViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is ManagerEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    CharacterManagerScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                ManagerAction.OnCharacterAddClick -> onCharacterAddClick()
                ManagerAction.OnCharacterDeleteClick ->  onCharacterDeleteClick()
                ManagerAction.OnCharacterSettingClick -> onCharacterSettingClick()

            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CharacterManagerScreen(
    modifier: Modifier = Modifier,
    state: ManagerState,
    onAction: (ManagerAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(ManagerAction.OnCharacterAddClick) },
                containerColor = LostArkBlue
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "주간 레이드 진행도",
                    style = Typography.headlineMedium.copy(
                        color = LostArkBlack
                    ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.gold),
                        contentDescription = "골드 이미지",
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = state.totalGold.toString(),
                        style = Typography.bodyMedium.copy(
                            fontSize = 14.sp
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            LinearProgressIndicator(
                progress = { state.raidProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = LostArkBlue,
                trackColor = LostArkGray,
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "획득 골드")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(R.drawable.gold),
                            contentDescription = "골드 이미지",
                            modifier = Modifier.size(35.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = state.totalEarnedGold.toString(),
                            style = Typography.bodyMedium.copy(fontSize = 14.sp)
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "남은 골드")
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(R.drawable.gold),
                            contentDescription = "골드 이미지",
                            modifier = Modifier.size(35.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        val remainGold = state.totalGold - state.totalEarnedGold
                        Text(
                            text = remainGold.toString(),
                            style = Typography.bodyMedium.copy(fontSize = 14.sp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.savedCharacterProgressList) { character ->
                    CharacterProgressListItem(
                        characterProgress = character,
                        onCharacterSettingClick = { onAction(ManagerAction.OnCharacterSettingClick) },
                        onCharacterDeleteClick = { onAction(ManagerAction.OnCharacterDeleteClick) }
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CharacterManagerScreenPreview() {
    LostarkTheme {
        CharacterManagerScreen(
            state = ManagerState(
                savedCharacterProgressList = listOf(
                    mockCharacterProgressContent(),
                    mockCharacterProgressContent(),
                    mockCharacterProgressContent(),
                    mockCharacterProgressContent(),
                )
            ),
            onAction = {  },
        )
    }
}

