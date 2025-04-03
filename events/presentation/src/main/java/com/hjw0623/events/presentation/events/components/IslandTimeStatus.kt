package com.hjw0623.events.presentation.events.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hjw0623.core.presentation.designsystem.LostArkDarkGray
import com.hjw0623.core.presentation.designsystem.LostArkGreen
import com.hjw0623.core.presentation.designsystem.LostarkTheme
import com.hjw0623.core.presentation.designsystem.Typography
import com.hjw0623.events.presentation.events.mockup.mockIslandContent
import com.hjw0623.events.presentation.events.model.IslandUi
import com.hjw0623.events.presentation.events.model.toIslandUi
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun IslandTimeStatus(
    island: IslandUi,
    now: LocalDateTime,
    durationSeconds: Long = 180L
) {
    val currentPeriod = island.startTimes.firstOrNull {
        val end = it.plusSeconds(durationSeconds)
        now in it..end
    }

    val nextStart = island.startTimes.filter { it.isAfter(now) }.minOrNull()

    val (status, color) = when {
        currentPeriod != null -> {
            val remain = Duration.between(now, currentPeriod.plusSeconds(durationSeconds))
            "닫히기까지 ${formatDuration(remain)}" to LostArkGreen
        }

        nextStart != null -> {
            val remain = Duration.between(now, nextStart)
            "열리기까지 ${formatDuration(remain)}" to MaterialTheme.colorScheme.primary
        }

        else -> "예정된 시간 없음" to LostArkDarkGray
    }

    Text(
        text = status,
        style = Typography.bodyMedium.copy(
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    )
}

fun formatDuration(duration: Duration): String {
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60
    val seconds = duration.seconds % 60
    return "%02d:%02d:%02d".format(hours, minutes, seconds)
}


@Preview
@Composable
private fun IslandTimeStatusPreview() {
    LostarkTheme {
        IslandTimeStatus(
            island = mockIslandContent().toIslandUi(),
            now = LocalDateTime.now(),
        )
    }
}
