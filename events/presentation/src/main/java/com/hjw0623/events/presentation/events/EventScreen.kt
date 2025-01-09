@file:OptIn(ExperimentalMaterial3Api::class)

package com.hjw0623.events.presentation.events

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import com.hjw0623.events.presentation.events.components.EventsListItem
import com.hjw0623.events.presentation.events.components.IslandListItem
import com.hjw0623.events.presentation.events.components.NoticeListItem
import com.hjw0623.events.presentation.events.mockup.mockEventContent
import com.hjw0623.events.presentation.events.mockup.mockIslandContent
import com.hjw0623.events.presentation.events.mockup.mockNoticeContent
import com.hjw0623.events.presentation.events.model.toEventUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import com.hjw0623.events.presentation.events.model.toNoticeUi
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.setValue
import com.hjw0623.events.presentation.events.components.IslandBottomSheet


@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    state: EventState,
    viewModel: EventsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is EventsEvent.Error -> Toast.makeText(
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 모험섬 섹션
            item {
                Text(
                    text = "모험섬",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(state.currentIslands) { island ->
                IslandListItem(
                    islandUi = island,
                    onClick = {
                        isSheetOpen = true
                        state.selectedIsland = island
                    }
                )
            }


            // Spacer
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 이벤트 섹션
            item {
                Text(
                    text = "이벤트",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.events) { event ->
                        EventsListItem(
                            eventUi = event,
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.link))
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .width(300.dp)
                                .height(180.dp)
                        )
                    }
                }
            }

            // Spacer
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 공지 섹션
            item {
                Text(
                    text = "공지",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // 고정된 높이로 제한
                ) {
                    items(state.notices) { notice ->
                        NoticeListItem(
                            noticeUi = notice,
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.link))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
                state.selectedIsland = null
            },
        ) {
            val selectedIsland = state.selectedIsland
            IslandBottomSheet(
                selectedIsland!!
            )

        }
    }
}


@Preview
@Composable
private fun EventScreenPreview() {
    LostarkTheme {
        EventScreen(
            state = EventState(

                currentIslands = listOf(
                    mockIslandContent().toIslandUi(),
                    mockIslandContent("수라도", "골드").toIslandUi(),
                    mockIslandContent("잔혹한 장난감 성", "해적 주화").toIslandUi()
                ),
                events = listOf(
                    mockEventContent().toEventUi(),
                    mockEventContent().toEventUi(),
                    mockEventContent().toEventUi()
                ),
                notices = listOf(
                    mockNoticeContent().toNoticeUi(),
                    mockNoticeContent().toNoticeUi(),
                    mockNoticeContent().toNoticeUi()
                )
            ),
        )
    }
}
