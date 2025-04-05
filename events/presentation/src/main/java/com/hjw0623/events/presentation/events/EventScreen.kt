@file:OptIn(ExperimentalMaterial3Api::class)

package com.hjw0623.events.presentation.events

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.core.presentation.ui.ObserveAsEvents
import com.hjw0623.events.presentation.events.components.EventsListItem
import com.hjw0623.events.presentation.events.components.IslandBottomSheet
import com.hjw0623.events.presentation.events.components.IslandListItem
import com.hjw0623.events.presentation.events.components.IslandTimeStatus
import com.hjw0623.events.presentation.events.components.NoticeListItem
import com.hjw0623.events.presentation.events.mockup.mockEventContent
import com.hjw0623.events.presentation.events.mockup.mockIslandContent
import com.hjw0623.events.presentation.events.mockup.mockNoticeContent
import com.hjw0623.events.presentation.events.model.IslandUi
import com.hjw0623.events.presentation.events.model.toEventUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import com.hjw0623.events.presentation.events.model.toNoticeUi
import org.koin.androidx.compose.koinViewModel
import java.time.DayOfWeek

@Composable
fun EventScreenRoot(
    viewModel: EventsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is EventsEvent.Error -> Toast.makeText(
                context,
                event.error.asString(context),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    EventScreen(
        state = state,
        onIslandSelected = { island ->
            viewModel.onIslandSelected(island)
        }
    )
}

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    state: EventState,
    onIslandSelected: (IslandUi?) -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isMorningSelected by rememberSaveable { mutableStateOf(true) }

    val isWeekend = state.now.dayOfWeek == DayOfWeek.SATURDAY || state.now.dayOfWeek == DayOfWeek.SUNDAY
    val morningHours = setOf(9, 11, 13)
    val eveningHours = setOf(19, 21, 23)

    val filteredIslands = if (isWeekend) {
        if (isMorningSelected) {
            state.currentIslands.filter { island ->
                island.startTimes.any { it.toLocalDate() == state.now.toLocalDate() && it.hour in morningHours }
            }
        } else {
            state.currentIslands.filter { island ->
                island.startTimes.any { it.toLocalDate() == state.now.toLocalDate() && it.hour in eveningHours }
            }
        }
    } else {
        state.currentIslands.filter { island ->
            island.startTimes.any { it.toLocalDate() == state.now.toLocalDate() }
        }
    }

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "모험섬",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = Typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    IslandTimeStatus(
                        now = state.now
                    )
                }
            }

            if (isWeekend) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "오전", style = Typography.bodyMedium)
                        Spacer(modifier = Modifier.width(8.dp))
                        Switch(
                            checked = !isMorningSelected,
                            onCheckedChange = { isMorningSelected = !it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "오후", style = Typography.bodyMedium)
                    }
                }
            }

            items(filteredIslands) { island ->
                IslandListItem(
                    islandUi = island,
                    onClick = {
                        isSheetOpen = true
                        onIslandSelected(island)
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    text = "이벤트",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

            item { Spacer(modifier = Modifier.height(16.dp)) }

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
                        .height(200.dp)
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

    if (isSheetOpen && state.selectedIsland != null) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
                onIslandSelected(null)
            },
            dragHandle = null
        ) {
            IslandBottomSheet(state.selectedIsland!!)
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
                    mockIslandContent().toIslandUi(),
                    mockIslandContent().toIslandUi()
                ),
                events = listOf(
                    mockEventContent().toEventUi(),
                    mockEventContent().toEventUi(),
                    mockEventContent().toEventUi(),
                    mockEventContent().toEventUi(),
                ),
                notices = listOf(
                    mockNoticeContent().toNoticeUi(),
                    mockNoticeContent().toNoticeUi(),
                    mockNoticeContent().toNoticeUi(),
                    mockNoticeContent().toNoticeUi(),
                ),
                selectedIsland = null
            ),
            onIslandSelected = {}
        )
    }
}
