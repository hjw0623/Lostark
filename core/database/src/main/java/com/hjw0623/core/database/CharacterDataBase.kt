package com.hjw0623.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hjw0623.core.database.dao.CharacterDao
import com.hjw0623.core.database.dao.SelectedRaidDao
import com.hjw0623.core.database.entity.CharacterEntity
import com.hjw0623.core.database.entity.SelectedRaidEntity
import com.hjw0623.core.database.mapper.ListConverters

@Database(
    entities = [
        CharacterEntity::class,
        SelectedRaidEntity::class],
    version = 1
)
@TypeConverters(ListConverters::class)
abstract class CharacterDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun selectedRaidDao(): SelectedRaidDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDataBase? = null

        fun getDatabase(context: Context): CharacterDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDataBase::class.java,
                    "lostark_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
