package com.hjw0623.events.domain

import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result


interface EventsRepository {
    suspend fun getIslands(): Result<List<Islands>, DataError.Network>
    suspend fun getEvents(): Result<List<Event>, DataError.Network>
    suspend fun getNotices(): Result<List<Notice>, DataError.Network>
}
