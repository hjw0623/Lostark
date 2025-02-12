package com.hjw0623.data

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.model.arkpassive.ArkPassive
import com.hjw0623.character.domain.model.card.CardList
import com.hjw0623.character.domain.model.engravings.Engravings
import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.domain.model.gems.Gems
import com.hjw0623.character.domain.model.profile.CharacterProfile
import com.hjw0623.core.data.networking.constructRoute
import com.hjw0623.core.data.networking.safeCall
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.data.model.arkpassive.ArkPassiveDto
import com.hjw0623.data.model.arkpassive.toDomain
import com.hjw0623.data.model.card.CardsSerializable
import com.hjw0623.data.model.card.toDomain
import com.hjw0623.data.model.engravings.EngravingsSerializable
import com.hjw0623.data.model.engravings.toDomain
import com.hjw0623.data.model.gear.GearSerializable
import com.hjw0623.data.model.gear.toDomain
import com.hjw0623.data.model.gems.GemsSerializable
import com.hjw0623.data.model.gems.toDomain
import com.hjw0623.data.model.profile.CharacterProfileSerializable
import com.hjw0623.data.model.profile.toDomain
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

    override suspend fun getGems(characterName: String): Result<Gems, DataError.Network> {
        return safeCall<GemsSerializable> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/gems")
            )
        }.map { response ->
            response.toDomain()
        }
    }

    override suspend fun getEngravings(characterName: String): Result<Engravings, DataError.Network> {
        return safeCall<EngravingsSerializable?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/engravings")
            )
        }.map { response ->
            response?.toDomain() ?: EngravingsSerializable(
                engravings = null,
                effects = null,
                arkPassiveEffects = null
            ).toDomain()
        }
    }

    override suspend fun getCards(characterName: String): Result<CardList, DataError.Network> {
        return safeCall<CardsSerializable> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/cards")
            )
        }.map { response ->
            response.toDomain()
        }
    }

    override suspend fun getArkPassive(characterName: String): Result<ArkPassive, DataError.Network> {
        return safeCall<ArkPassiveDto> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/arkpassive")
            )
        }.map { response ->
            response.toDomain()
        }
    }


}