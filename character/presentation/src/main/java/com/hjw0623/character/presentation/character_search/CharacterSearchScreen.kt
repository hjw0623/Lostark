package com.hjw0623.character.presentation.character_search

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hjw0623.core.presentation.designsystem.LostArkBlack
import com.hjw0623.core.presentation.designsystem.LostArkGray
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterSearchScreenRoot(
    onSearchClick: (String) -> Unit,
    viewModel: CharacterSearchViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CharacterSearchEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()
        }
    }
    CharacterSearchScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is CharacterSearchAction.OnSearchClick -> onSearchClick(action.searchInput)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CharacterSearchScreen(
    modifier: Modifier = Modifier,
    state: CharacterSearchState,
    onAction: (CharacterSearchAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchQuery by remember { mutableStateOf(state.searchQuery) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = searchQuery, // ✅ UI 상태 사용
            onValueChange = { newInputQuery ->
                searchQuery = newInputQuery // ✅ UI에서 즉시 반영
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
                                searchQuery = "" // ✅ UI 즉시 초기화
                                onAction(CharacterSearchAction.ClearSearchQuery)
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
                    if (searchQuery.isNotBlank()) { // ✅ searchQuery 사용
                        onAction(CharacterSearchAction.OnSearchClick(searchQuery.trim()))
                        keyboardController?.hide()
                    }
                }
            )
        )

        if (state.searchHistory.isNotEmpty()) {
            LazyColumn {
                items(state.searchHistory) { historyItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = historyItem,
                            fontSize = 16.sp,
                            color = LostArkBlack,
                            modifier = Modifier
                                .clickable {
                                    searchQuery = historyItem // ✅ UI에서 검색어 즉시 반영
                                    onAction(CharacterSearchAction.OnSearchClick(historyItem))
                                }
                                .weight(1f)
                        )
                        Icon(
                            modifier = Modifier.clickable {
                                onAction(CharacterSearchAction.DeleteSearchHistory(historyItem))
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        } else {
            Text(
                text = "최근 검색 기록이 없습니다.",
                modifier = Modifier.padding(16.dp),
                color = LostArkGray
            )
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
private fun CharacterSearchScreenPreview() {
    LostarkTheme {
        CharacterSearchScreen(
            state = CharacterSearchState(),
            onAction = { }
        )
    }
}
