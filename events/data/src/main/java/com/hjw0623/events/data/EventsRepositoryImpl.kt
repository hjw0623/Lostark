package com.hjw0623.events.data

import com.hjw0623.core.data.networking.constructRoute
import com.hjw0623.core.data.networking.safeCall
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.events.data.events.model.EventSerializable
import com.hjw0623.events.data.events.toDomain
import com.hjw0623.events.data.islands.model.IslandsSerializable
import com.hjw0623.events.data.islands.toDomain
import com.hjw0623.events.data.notices.model.NoticeSerializable
import com.hjw0623.events.data.notices.toDomain
import com.hjw0623.events.domain.model.Event
import com.hjw0623.events.domain.EventsRepository
import com.hjw0623.events.domain.model.Islands
import com.hjw0623.events.domain.model.Notice
import io.ktor.client.HttpClient
import io.ktor.client.request.get


class EventsRepositoryImpl(
    private val httpClient: HttpClient
) : EventsRepository {
    override suspend fun getIslands(): Result<List<Islands>, DataError.Network> {
        return safeCall<List<IslandsSerializable>> {
            httpClient.get(
                urlString = constructRoute("/gamecontents/calendar")
            )
        }.map { response ->
            response.map { it.toDomain() }.filter {
                it.categoryName == "모험 섬"
            }
        }
    }

    override suspend fun getEvents(): Result<List<Event>, DataError.Network> {
        return safeCall<List<EventSerializable>> {
            httpClient.get(
                urlString = constructRoute("/news/events")
            )
        }.map { response ->
            response.map { it.toDomain() }
        }
    }

    override suspend fun getNotices(): Result<List<Notice>, DataError.Network> {
        return safeCall<List<NoticeSerializable>> {
            httpClient.get(
                urlString = constructRoute("/news/notices")
            )
        }.map { response ->
            response.map { it.toDomain() }
        }
    }
}

