package com.hjw0623.data

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.domain.model.profile.CharacterProfile
import com.hjw0623.core.data.networking.constructRoute
import com.hjw0623.core.data.networking.safeCall
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.data.model.gear.GearSerializable
import com.hjw0623.data.model.profile.CharacterProfileSerializable
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.encodeURLPath

class CharacterRepositoryImpl(
    private val httpClient: HttpClient
): CharacterRepository {
    override suspend fun getCharacterProfile(characterName: String): Result<CharacterProfile, DataError.Network> {
        return safeCall<CharacterProfileSerializable> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/profiles")
            )
        }.map { response ->
            response.toDomain()
        }
    }

    override suspend fun getGear(characterName: String): Result<List<Gear>, DataError.Network> {
        return safeCall<List<GearSerializable>> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/equipment")
            )
        }.map { response ->
            response.map { it.toDomain() }
        }
    }
}