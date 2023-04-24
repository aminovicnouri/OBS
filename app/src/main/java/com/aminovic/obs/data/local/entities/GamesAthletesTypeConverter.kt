package com.aminovic.obs.data.local.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GamesAthletesTypeConverter {
    @TypeConverter
    fun fromJson(json: String): List<AthleteEntity> {
        val listType = object : TypeToken<List<AthleteEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(list: List<AthleteEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}