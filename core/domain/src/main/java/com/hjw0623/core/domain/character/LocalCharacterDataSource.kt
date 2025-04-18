package com.hjw0623.core.domain.character

import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias CharacterName = String
typealias RaidId = String

interface LocalCharacterDataSource {
    fun getCharacters(): Flow<List<RoomCharacter>>
    suspend fun upsertCharacter(roomCharacter: RoomCharacter) : Result<CharacterName, DataError.Local>
    suspend fun getCharacter(characterName: String): RoomCharacter?
    suspend fun deleteCharacter(characterName: String)

    suspend fun upsertSelectedRaid(raid: SelectedRaid): Result<RaidId, DataError.Local>
    suspend fun getSelectedRaids(characterId: String): Flow<List<SelectedRaid>>
    suspend fun deleteSelectedRaids(characterId: String)
    suspend fun getSelectedRaid(characterId: String, raidName: String): SelectedRaid?
}
