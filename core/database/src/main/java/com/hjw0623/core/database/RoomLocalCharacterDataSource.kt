package com.hjw0623.core.database

import android.database.sqlite.SQLiteFullException
import com.hjw0623.core.database.dao.CharacterDao
import com.hjw0623.core.database.dao.SelectedRaidDao
import com.hjw0623.core.database.mapper.toCharacter
import com.hjw0623.core.database.mapper.toCharacterEntity
import com.hjw0623.core.database.mapper.toSelectedRaid
import com.hjw0623.core.database.mapper.toSelectedRaidEntity
import com.hjw0623.core.domain.character.Character
import com.hjw0623.core.domain.character.CharacterName
import com.hjw0623.core.domain.character.LocalCharacterDataSource
import com.hjw0623.core.domain.character.RaidId
import com.hjw0623.core.domain.character.SelectedRaid
import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalCharacterDataSource(
    private val characterDao: CharacterDao,
    private val selectedRaidDao: SelectedRaidDao
) : LocalCharacterDataSource {

    override fun getCharacters(): Flow<List<Character>> {
        return characterDao.getCharacters()
            .map { entities -> entities.map { it.toCharacter() } }
    }

    override suspend fun upsertCharacter(character: Character): Result<CharacterName, DataError.Local> {
        return try {
            val entity = character.toCharacterEntity()
            characterDao.upsertCharacter(entity)
            Result.Success(entity.characterName)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getCharacter(characterName: String): Character? {
        return characterDao.getCharacter(characterName)?.toCharacter()
    }

    override suspend fun deleteCharacter(characterName: String) {
        selectedRaidDao.deleteSelectedRaids(characterName)
        characterDao.deleteCharacter(characterName)
    }

    override suspend fun upsertSelectedRaid(raid: SelectedRaid): Result<RaidId, DataError.Local> {
        return try {
            val entity = raid.toSelectedRaidEntity()
            selectedRaidDao.upsertSelectedRaid(entity)
            Result.Success(entity.characterId)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getSelectedRaids(characterId: String): Flow<List<SelectedRaid>> {
        return selectedRaidDao.getSelectedRaidsForCharacter(characterId)
            .map { entities -> entities.map { it.toSelectedRaid() } }
    }

    override suspend fun deleteSelectedRaids(characterId: String) {
        selectedRaidDao.deleteSelectedRaids(characterId)
    }
}
