package io.rohail.androidomdbsearch.base.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.rohail.androidomdbsearch.ui.search.data.model.Rating

class Converter {

    @TypeConverter
    fun from(value: String): List<Rating> =
        Gson().fromJson(value, object : TypeToken<List<Rating>>() {}.type)

    @TypeConverter
    fun toString(value: List<Rating>): String = Gson().toJson(value)
}