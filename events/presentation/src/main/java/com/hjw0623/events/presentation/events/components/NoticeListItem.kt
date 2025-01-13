package com.hjw0623.events.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically // Row 내 세로 정렬
    ) {
        // Type 텍스트: 중앙 정렬
        Text(
            modifier = Modifier
                .padding(8.dp)
                .border(width = 2.dp, color = MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            text = noticeUi.type,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center // 가로로 중앙 정렬
        )

        Spacer(modifier = Modifier.width(8.dp)) // Type과 Title 사이의 간격

        // Title 텍스트: 왼쪽 정렬
        Text(
            modifier = Modifier
                .fillMaxWidth() // 남은 공간 채우기
                .align(Alignment.CenterVertically), // Row 안에서 세로로 가운데 정렬
            text = noticeUi.title,
            textAlign = TextAlign.Start, // 텍스트를 왼쪽 정렬
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
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

