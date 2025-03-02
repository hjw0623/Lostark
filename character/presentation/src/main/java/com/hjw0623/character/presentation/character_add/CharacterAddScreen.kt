package com.hjw0623.character.presentation.character_add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hjw0623.character.presentation.character_add.components.CharacterAddDialog
import com.hjw0623.character.presentation.character_add.components.CharacterAddListItem
import com.hjw0623.character.presentation.character_add.model.CharacterAddUi
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterAddScreenRoot(
    onBackClick: () -> Unit,
    viewModel: CharacterAddViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterAddEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()

            is CharacterAddEvent.AddCharacter -> Toast.makeText(
                context,
                "${event.character.characterName} 추가 완료!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    CharacterAddScreen(
        state = state,
        onAction = { action ->
            when (action) {
                CharacterAddAction.OnBackClick -> onBackClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun CharacterAddScreen(
    modifier: Modifier = Modifier,
    state: CharacterAddState,
    onAction: (CharacterAddAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchQuery by remember { mutableStateOf(state.searchQuery) }

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
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .clickable { onAction(CharacterAddAction.OnBackClick) }
                    .padding(8.dp)
            )

            Text(
                text = "캐릭터 추가",
                style = Typography.bodyMedium.copy(fontSize = 20.sp)
            )
        }
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
                .clickable {
                    keyboardController?.show()
                },
            cursorBrush = SolidColor(LostArkBlack),
            maxLines = 1,
            singleLine = true,
            textStyle = Typography.bodyMedium.copy(fontSize = 18.sp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if (searchQuery.isEmpty()) {
                            Text(text = "캐릭터 이름을 입력하세요...", color = LostArkGray)
                        }
                        innerTextField()
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                searchQuery = ""
                                onAction(CharacterAddAction.OnClearQueryClick)
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchQuery.isNotBlank()) {
                        onAction(CharacterAddAction.OnSearchClick(searchQuery.trim()))
                        keyboardController?.hide()
                    }
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            state.searchCharacter?.let { searchCharacter ->
                item {
                    Column {
                        Text(
                            text = "검색 결과",
                            style = Typography.bodyMedium.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        CharacterAddListItem(
                            character = searchCharacter,
                            onClick = {
                                onAction(CharacterAddAction.OnShowDialog(it))
                            }
                        )
                    }
                }
            }

            val groupedCharacters =
                state.characterList.groupBy { it.serverName }
            if (groupedCharacters.isNotEmpty()) {
                item {
                    Text(
                        text = "보유 캐릭터",
                        style = Typography.bodyMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                groupedCharacters.forEach { (serverName, characters) ->
                    item {
                        Text(
                            text = serverName,
                            style = Typography.bodyMedium.copy(fontSize = 16.sp),
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }


                    items(characters) { character ->
                        CharacterAddListItem(
                            character = character,
                            onClick = {
                                onAction(CharacterAddAction.OnShowDialog(it))
                            }
                        )
                    }
                }
            }
        }
    }
    if (state.showDialog && state.selectedCharacter != null) {
        CharacterAddDialog(
            character = state.selectedCharacter,
            onConfirm = {
                onAction(CharacterAddAction.OnAddCharacterClick(state.selectedCharacter))
            },
            onDismiss = {
                onAction(CharacterAddAction.OnDismissAddClick)
            }
        )

    }

}

@Preview(
    showBackground = true
)
@Composable
private fun CharacterAddScreenPreview() {
    LostarkTheme {
        CharacterAddScreen(
            state = CharacterAddState(
                searchCharacter = CharacterAddUi(
                    classIcon = "",
                    serverName = "카단",
                    characterName = "검색 캐릭터",
                    characterClassName = "창술사",
                    itemAvgLevel = "1,664.7",
                ),
                characterList = listOf(
                    CharacterAddUi(
                        classIcon = "",
                        serverName = "카단",
                        characterName = "테스트 캐릭터",
                        characterClassName = "창술사",
                        itemAvgLevel = "1,664.7",
                    ),
                    CharacterAddUi(
                        classIcon = "",
                        serverName = "카단",
                        characterName = "테스트 캐릭터",
                        characterClassName = "창술사",
                        itemAvgLevel = "1,664.7",
                    ),
                    CharacterAddUi(
                        classIcon = "",
                        serverName = "카단",
                        characterName = "테스트 캐릭터",
                        characterClassName = "창술사",
                        itemAvgLevel = "1,664.7",
                    ),
                    CharacterAddUi(
                        classIcon = "",
                        serverName = "카단",
                        characterName = "테스트 캐릭터",
                        characterClassName = "창술사",
                        itemAvgLevel = "1,664.7",
                    ),
                    CharacterAddUi(
                        classIcon = "",
                        serverName = "카단",
                        characterName = "테스트 캐릭터",
                        characterClassName = "창술사",
                        itemAvgLevel = "1,664.7",
                    )
                )
            ),
            onAction = { }
        )
    }
}
