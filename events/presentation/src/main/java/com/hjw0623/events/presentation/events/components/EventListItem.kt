package com.hjw0623.events.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.events.presentation.events.mockup.mockEventContent
import com.hjw0623.events.presentation.events.model.EventUi
import com.hjw0623.events.presentation.events.model.toEventUi

@Composable
fun EventsListItem(
    eventUi: EventUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        AsyncImage(
            model = eventUi.thumbnail,
            contentDescription = "event thumbnail",
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(16.dp))
                .padding(5.dp)
                .clickable { onClick() }
        )
        Text(
            text = eventUi.dateRange,
            style = Typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun EventsListItemPreview() {
    LostarkTheme {
        EventsListItem(
            eventUi = previewEvent,
            onClick = {}
        )
    }
}

internal val previewEvent = mockEventContent().toEventUi()

