package com.hjw0623.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hjw0623.core.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE characterName = :name")
    suspend fun getCharacter(name: String): CharacterEntity?

    @Upsert
    suspend fun upsertCharacter(character: CharacterEntity)

    @Query("DELETE FROM character WHERE characterName = :characterName")
    suspend fun deleteCharacter(characterName: String)

}


