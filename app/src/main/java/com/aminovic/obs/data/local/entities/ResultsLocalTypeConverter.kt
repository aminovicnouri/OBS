package com.aminovic.obs.data.local.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ResultsEntityTypeConverter {
    @TypeConverter
    fun fromJson(json: String): List<ResultEntity> {
        val listType = object : TypeToken<List<ResultEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(list: List<ResultEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}