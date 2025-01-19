package com.hjw0623.data

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.model.CharacterProfile
import com.hjw0623.core.data.networking.constructRoute
import com.hjw0623.core.data.networking.safeCall
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.data.model.CharacterProfileSerializable
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.encodeURLPath

class CharacterRepositoryImpl(
    private val httpClient: HttpClient
): CharacterRepository {
    override suspend fun getCharacterProfile(characterName: String): Result<CharacterProfile, DataError.Network> {
        return safeCall<CharacterProfileSerializable> {
            val encodedCharacterName = characterName.encodeURLPath()
            httpClient.get(
                urlString = constructRoute("https://developer-lostark.game.onstove.com/armories/characters/$encodedCharacterName/profiles")
            )
        }.map { response ->
            response.toDomain()
        }
    }
}