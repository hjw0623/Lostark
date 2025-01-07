package com.hjw0623.events.presentation.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.events.presentation.events.components.EventsListItem
import com.hjw0623.events.presentation.events.components.IslandListItem
import com.hjw0623.events.presentation.events.components.NoticeListItem
import com.hjw0623.events.presentation.events.mockup.mockEventContent
import com.hjw0623.events.presentation.events.mockup.mockIslandContent
import com.hjw0623.events.presentation.events.mockup.mockNoticeContent
import com.hjw0623.events.presentation.events.model.toEventUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import com.hjw0623.events.presentation.events.model.toNoticeUi

@Composable
fun EventScreen(
    state: EventState,
    modifier: Modifier = Modifier
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                //.verticalScroll(rememberScrollState())
                .padding(16.dp),
        ) {
            Text(
                text = "모험섬",
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.currentIslands) { island ->
                    IslandListItem(
                        islandUi = island,
                        onClick = {  }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "이벤트",
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.events) { event ->
                    EventsListItem(
                        eventUi = event,
                        onClick = { /* TODO: Click action */ },
                        modifier = Modifier
                            .width(300.dp)
                            .height(180.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "공지",
                color = MaterialTheme.colorScheme.onBackground,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                items(state.notices) { notice ->
                    NoticeListItem(
                        noticeUi = notice,
                        onClick = { /* TODO: Click action */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
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
            )
        )
    }
}
