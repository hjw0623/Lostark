package com.hjw0623.events.domain

import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.events.domain.model.Event
import com.hjw0623.events.domain.model.Islands
import com.hjw0623.events.domain.model.Notice


interface EventsRepository {
    suspend fun getIslands(): Result<List<Islands>, DataError.Network>
    suspend fun getEvents(): Result<List<Event>, DataError.Network>
    suspend fun getNotices(): Result<List<Notice>, DataError.Network>
}
