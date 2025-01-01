package com.hjw0623.events.data

import com.hjw0623.core.data.networking.events.model.EventSerializable
import com.hjw0623.core.data.networking.events.toDomain
import com.hjw0623.core.data.networking.islands.model.IslandsSerializable
import com.hjw0623.core.data.networking.islands.toDomain
import com.hjw0623.core.data.networking.get
import com.hjw0623.core.data.networking.notices.model.NoticeSerializable
import com.hjw0623.core.data.networking.notices.toDomain
import com.hjw0623.core.domain.events.model.Event
import com.hjw0623.core.domain.islands.model.Islands
import com.hjw0623.core.domain.notices.model.Notice
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.events.domain.EventsRepository
import io.ktor.client.HttpClient


class EventsRepositoryImpl(
    private val httpClient: HttpClient
) : EventsRepository {

    private val apiKey = "bearer " + BuildConfig.API_KEY
    private val headerMap = mapOf(
        "Authorization" to apiKey,
        "Accept" to "application/json"
    )

    override suspend fun getIslands(): Result<List<Islands>, DataError.Network> {
        val response: Result<ArrayList<IslandsSerializable>, DataError.Network> = httpClient.get(
            route = "/gamecontents/calendar",
            headers = headerMap
        )
        val result = response.map { list ->
            list.map { it.toDomain() }
                .filter { it.categoryName == "모험 섬" }
        }
        return result
    }

    override suspend fun getEvents(): Result<List<Event>, DataError.Network> {
        val response: Result<List<EventSerializable>, DataError.Network> = httpClient.get(
            route = "/news/events",
            headers = headerMap
        )
        val result = response.map { list ->
            list.map {
                it.toDomain()
            }
        }
        return result
    }

    override suspend fun getNotices(): Result<List<Notice>, DataError.Network> {
        val response: Result<List<NoticeSerializable>, DataError.Network> = httpClient.get(
            route = "/news/notices",
            headers = headerMap
        )
        val result = response.map { list ->
            list.map {
                it.toDomain()
            }
        }
        return result
    }
}

