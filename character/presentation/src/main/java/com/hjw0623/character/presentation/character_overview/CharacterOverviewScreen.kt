package com.hjw0623.character.presentation.character_overview

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hjw0623.character.presentation.character_overview.components.CharacterDetailTab
import com.hjw0623.character.presentation.character_overview.components.CharacterProfile
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterOverviewScreenRoot(
    characterName: String,
    onBackClick: () -> Unit,
    viewModel: CharacterOverviewViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterOverviewEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()

            CharacterOverviewEvent.NavigateBack -> {
                onBackClick()
            }
        }
    }
    LaunchedEffect(characterName) {
        viewModel.setCharacterName(characterName)
    }
    CharacterOverviewScreen(
        state = state,
        onSiblingClick = { siblingName ->
            viewModel.setCharacterName(siblingName)
        }
    )
}

@Composable
fun CharacterOverviewScreen(
    modifier: Modifier = Modifier,
    state: CharacterOverviewState,
    onSiblingClick: (String) -> Unit
) {


    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = modifier.fillMaxSize()) {
            state.characterProfile?.let {
                CharacterProfile(
                    characterProfileUi = it,
                    modifier = Modifier.fillMaxHeight(0.35f)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = LostArkWhite)
            ) {
                CharacterDetailTab(
                    state = state,
                    onSiblingClick = onSiblingClick
                )
            }
        }
    }
}



@Preview
@Composable
private fun CharacterScreenPreview() {
    LostarkTheme {
        CharacterOverviewScreen(
            state = CharacterOverviewState(),
            onSiblingClick = { }
        )
    }
}
