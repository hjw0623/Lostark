package com.hjw0623.data

import com.hjw0623.character.domain.CharacterRepository
import com.hjw0623.character.domain.model.arkpassive.ArkPassive
import com.hjw0623.character.domain.model.avatar.Avatar
import com.hjw0623.character.domain.model.card.CardList
import com.hjw0623.character.domain.model.collectibles.Collectible
import com.hjw0623.character.domain.model.engravings.Engravings
import com.hjw0623.character.domain.model.gear.Gear
import com.hjw0623.character.domain.model.gems.Gems
import com.hjw0623.character.domain.model.profile.CharacterProfile
import com.hjw0623.character.domain.model.siblings.Sibling
import com.hjw0623.character.domain.model.skill.Skill
import com.hjw0623.core.data.networking.constructRoute
import com.hjw0623.core.data.networking.safeCall
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import com.hjw0623.core.domain.util.map
import com.hjw0623.data.model.arkpassive.ArkPassiveDto
import com.hjw0623.data.model.arkpassive.toDomain
import com.hjw0623.data.model.avatar.AvatarSerializable
import com.hjw0623.data.model.avatar.toDomain
import com.hjw0623.data.model.card.CardsSerializable
import com.hjw0623.data.model.card.toDomain
import com.hjw0623.data.model.collectibles.CollectibleSerializable
import com.hjw0623.data.model.collectibles.toDomain
import com.hjw0623.data.model.engravings.EngravingsSerializable
import com.hjw0623.data.model.engravings.toDomain
import com.hjw0623.data.model.gear.GearSerializable
import com.hjw0623.data.model.gear.toDomain
import com.hjw0623.data.model.gems.GemsSerializable
import com.hjw0623.data.model.gems.toDomain
import com.hjw0623.data.model.profile.CharacterProfileSerializable
import com.hjw0623.data.model.profile.toDomain
import com.hjw0623.data.model.siblings.SiblingSerializable
import com.hjw0623.data.model.siblings.toDomain
import com.hjw0623.data.model.skill.SkillSerializable
import com.hjw0623.data.model.skill.toDomain
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.encodeURLPath

class CharacterRepositoryImpl(
    private val httpClient: HttpClient
): CharacterRepository {
    override suspend fun getCharacterProfile(characterName: String): Result<CharacterProfile, DataError.Network> {
        return safeCall<CharacterProfileSerializable?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/profiles")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            response.toDomain()
        }
    }

    override suspend fun getGear(characterName: String): Result<List<Gear>, DataError.Network> {
        return safeCall<List<GearSerializable>?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/equipment")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            if (response.isEmpty()) {
                return Result.Error(DataError.Network.EMPTY_RESPONSE)
            }
            response.map { it.toDomain() }
        }
    }

    override suspend fun getGems(characterName: String): Result<Gems, DataError.Network> {
        return safeCall<GemsSerializable?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/gems")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            response.toDomain()
        }
    }

    override suspend fun getEngravings(characterName: String): Result<Engravings, DataError.Network> {
        return safeCall<EngravingsSerializable?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/engravings")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            response.toDomain()
        }
    }

    override suspend fun getCards(characterName: String): Result<CardList, DataError.Network> {
        return safeCall<CardsSerializable?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/cards")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            response.toDomain()
        }
    }

    override suspend fun getArkPassive(characterName: String): Result<ArkPassive, DataError.Network> {
        return safeCall<ArkPassiveDto?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/arkpassive")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            response.toDomain()
        }
    }

    override suspend fun getSkill(characterName: String): Result<List<Skill>, DataError.Network> {
        return safeCall<List<SkillSerializable>?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/combat-skills")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            if (response.isEmpty()) {
                return Result.Error(DataError.Network.EMPTY_RESPONSE)
            }
            response.map { it.toDomain() }
        }
    }

    override suspend fun getAvatar(characterName: String): Result<List<Avatar>, DataError.Network> {
        return safeCall<List<AvatarSerializable>?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/avatars")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            if (response.isEmpty()) {
                return Result.Error(DataError.Network.EMPTY_RESPONSE)
            }
            response.map { it.toDomain() }
        }
    }

    override suspend fun getSibling(characterName: String): Result<List<Sibling>, DataError.Network> {
        return safeCall<List<SiblingSerializable>?> {
            httpClient.get(
                urlString = constructRoute("/characters/${characterName.encodeURLPath()}/siblings")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.EMPTY_CHARACTER_RESPONSE)
            }
            if (response.isEmpty()) {
                return Result.Error(DataError.Network.EMPTY_RESPONSE)
            }
            response.map { it.toDomain() }
        }
    }

    override suspend fun getCollectibles(characterName: String): Result<List<Collectible>, DataError.Network> {
        return safeCall<List<CollectibleSerializable>?> {
            httpClient.get(
                urlString = constructRoute("/armories/characters/${characterName.encodeURLPath()}/collectibles")
            )
        }.map { response ->
            if (response == null) {
                return Result.Error(DataError.Network.NOT_FOUND)
            }
            if (response.isEmpty()) {
                return Result.Error(DataError.Network.EMPTY_RESPONSE)
            }
            response.map { it.toDomain() }
        }
    }
}