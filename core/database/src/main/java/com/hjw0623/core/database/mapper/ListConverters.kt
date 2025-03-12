package com.hjw0623.core.database.mapper

import androidx.room.TypeConverter

class ListConverters {
    @TypeConverter
    fun fromBooleanList(list: List<Boolean>): String {
        return list.joinToString(separator = ",") { it.toString()}
    }

    @TypeConverter
    fun toBooleanList(value: String): List<Boolean> {
        return value.split(",").map { it.toBoolean() }
    }

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }

}