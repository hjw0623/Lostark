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
import java.time.Duration
import java.time.LocalDateTime

@Composable
fun IslandTimeStatus(
    now: LocalDateTime,
) {
    val timeSlots = getIslandTimeSlots(now)

    val currentSlot = timeSlots.firstOrNull { (start, end) ->
        now in start..end
    }

    val nextSlot = timeSlots.firstOrNull { (start, _) ->
        now.isBefore(start)
    }

    val (status, color) = when {
        currentSlot != null -> {
            val remain = Duration.between(now, currentSlot.second)
            "닫히기까지 ${formatDuration(remain)}" to LostArkGreen
        }

        nextSlot != null -> {
            val remain = Duration.between(now, nextSlot.first)
            "열리기까지 ${formatDuration(remain)}" to MaterialTheme.colorScheme.primary
        }

        else -> {
            val tomorrowFirst = getIslandTimeSlots(now.plusDays(1)).first().first
            val remain = Duration.between(now, tomorrowFirst)
            "열리기까지 ${formatDuration(remain)}" to LostArkDarkGray
        }
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

fun getIslandTimeSlots(now: LocalDateTime): List<Pair<LocalDateTime, LocalDateTime>> {
    val isWeekend = now.dayOfWeek.value == 6 || now.dayOfWeek.value == 7
    val hours = if (isWeekend) listOf(9, 11, 13, 19, 21, 23) else listOf(11, 13, 19, 21, 23)

    return hours.map { hour ->
        val start = now.toLocalDate().atTime(hour, 0)
        val end = start.plusSeconds(180)
        start to end
    }
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
        IslandTimeStatus(now = LocalDateTime.now())
    }
}
