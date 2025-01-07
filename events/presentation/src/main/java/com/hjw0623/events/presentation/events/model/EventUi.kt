package com.hjw0623.events.presentation.events.model

import com.hjw0623.events.domain.Event
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class EventUi(
    val link: String,
    val thumbnail: String,
    val dateRange: String
)

fun Event.toEventUi(): EventUi {
    return EventUi(
        link = link,
        thumbnail = thumbnail,
        dateRange = formatDateRange(startDate, endDate)
    )
}

fun formatDateRange(startDate: String, endDate: String): String {
    // ISO8601 형식의 날짜를 LocalDateTime으로 파싱
    val startDateTime = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME)
    val endDateTime = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_DATE_TIME)

    // 원하는 출력 형식 지정
    val startFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val endFormatter = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss")

    // 날짜 포맷팅
    val formattedStart = startDateTime.format(startFormatter)
    val formattedEnd = endDateTime.format(endFormatter)

    // 변환된 문자열 반환
    return "$formattedStart - $formattedEnd"
}