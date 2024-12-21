package com.hjw0623.events.domain

import com.hjw0623.core.domain.events.model.EventsItem
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result


interface EventsRepository {
    suspend fun getEvents(): Result<List<EventsItem>, DataError.Network>
}
