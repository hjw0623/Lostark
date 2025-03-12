package com.hjw0623.character.presentation.character_setting

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hjw0623.character.domain.model.raid.RaidInfo
import com.hjw0623.character.presentation.character_setting.components.RaidListItem
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents

@Composable
fun CharacterSettingScreenRoot(
    characterName: String,
    onBackClick: () -> Unit,
    viewModel: CharacterSettingViewModel
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterSettingEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()
            CharacterSettingEvent.NavigateBack -> {
                onBackClick()
            }
            CharacterSettingEvent.RaidSavedSuccess -> {
                Toast.makeText(
                    context,
                    "레이드가 저장 되었습니다.",
                    Toast.LENGTH_LONG
                ).show()
                onBackClick()
            }

        }
    }

    LaunchedEffect(characterName) {
        viewModel.setCharacterName(characterName)
    }

    CharacterSettingScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CharacterSettingScreen(
    modifier: Modifier = Modifier,
    state: CharacterSettingState,
    onAction: (CharacterSettingAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .clickable { onAction(CharacterSettingAction.OnBackClick) }
                    .padding(8.dp)
            )

            Text(
                text = "레이드 선택",
                style = Typography.bodyMedium.copy(fontSize = 20.sp)
            )
        }

        Text(
            text = "선택 가능한 레이드 (${state.selectedRaids.size}/3)",
            style = Typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(state.availableRaids) {raid ->
                RaidListItem(
                    raid = raid,
                    isSelected = state.selectedRaids.contains(raid),
                    onRaidClick = { onAction(CharacterSettingAction.OnRaidSelected(raid)) }
                )
            }
        }

        Button(
            onClick = { onAction(CharacterSettingAction.OnSaveClick) },
            enabled = state.selectedRaids.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "저장")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CharacterSettingScreenPreview() {
    LostarkTheme {
        CharacterSettingScreen(
            state = CharacterSettingState(

                availableRaids = listOf(
                    RaidInfo("아브렐슈드", "노말", 1490, 4, listOf(1000, 1000, 1000, 1600)),
                    RaidInfo("아브렐슈드", "하드", 1490, 4, listOf(1200, 1200, 1200, 2000))
                ),
                selectedRaids = listOf(
                    RaidInfo("아브렐슈드", "노말", 1490, 4, listOf(1000, 1000, 1000, 1600))
                )
            ),
            onAction = { }
        )
    }
}
