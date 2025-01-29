package com.hjw0623.character.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hjw0623.character.presentation.components.CharacterDetailTab
import com.hjw0623.character.presentation.components.CharacterProfile
import com.hjw0623.character.presentation.mockup.mockCharacterProfileContent
import com.hjw0623.core.presentation.designsystem.LostArkWhite
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    state: CharacterState,
    viewModel: CharacterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()
        }
    }
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().systemBarsPadding()
        ) {

            state.characterProfile?.let {
                CharacterProfile(
                    characterProfileUi = it,
                    modifier = Modifier.fillMaxHeight(0.35f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = LostArkWhite)
                ) {
                    CharacterDetailTab(
                        state = state,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterScreenPreview() {
    LostarkTheme {
        CharacterScreen(
            state = CharacterState(
                searchedCharacterName = "드래디어",
            )
        )
    }
}
