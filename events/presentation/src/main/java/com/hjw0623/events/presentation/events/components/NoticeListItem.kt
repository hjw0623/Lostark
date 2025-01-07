package com.hjw0623.events.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.events.presentation.events.mockup.mockNoticeContent
import com.hjw0623.events.presentation.events.model.NoticeUi
import com.hjw0623.events.presentation.events.model.toNoticeUi

@Composable
fun NoticeListItem(
    noticeUi: NoticeUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Black)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            text = noticeUi.type
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = noticeUi.title,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun NoticeListItemPreview() {
    LostarkTheme {
        NoticeListItem(
            noticeUi = previewNotice,
            onClick = { }
        )
    }
}

internal val previewNotice = mockNoticeContent().toNoticeUi()

