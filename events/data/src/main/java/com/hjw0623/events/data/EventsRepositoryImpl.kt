package com.hjw0623.events.data

import com.hjw0623.core.data.networking.events.model.EventsItemSerializable
import com.hjw0623.core.data.networking.events.toDomain
import com.hjw0623.core.data.networking.get
import com.hjw0623.core.domain.events.model.EventsItem
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.events.domain.EventsRepository
import io.ktor.client.HttpClient


class EventsRepositoryImpl(
    private val httpClient: HttpClient
) : EventsRepository {

    override suspend fun getEvents(): Result<List<EventsItem>, DataError.Network> {
        val apiKey = "bearer " + BuildConfig.API_KEY

        // API 호출 결과를 받아옴
        val response: Result<ArrayList<EventsItemSerializable>, DataError.Network> = httpClient.get(
            route = "/gamecontents/calendar",
            headers = mapOf(
                "Authorization" to apiKey,
                "Accept" to "application/json"
            )
        )

        // Result 매핑: List -> ArrayList로 변환
        return response.map { list ->
            ArrayList(list.map { it.toDomain() }) // List<EventsItemSerializable> -> ArrayList<EventsItem>
        }
    }
}

