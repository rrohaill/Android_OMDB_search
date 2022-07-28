package io.rohail.androidomdbsearch.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

@Database(entities = [DetailResponse::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class MyDB : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}