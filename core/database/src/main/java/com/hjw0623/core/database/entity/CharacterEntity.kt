package com.hjw0623.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey val characterName: String,
    val className: String,
    val server: String,
    val avgItemLevel: Double,
)
