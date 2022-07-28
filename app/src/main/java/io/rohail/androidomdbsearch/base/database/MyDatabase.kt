package io.rohail.androidomdbsearch.base.database

import android.content.Context
import androidx.room.Room
import io.rohail.androidomdbsearch.R

object MyDatabase {

    fun getInstance(context: Context): MyDB = Room.databaseBuilder(
        context.applicationContext,
        MyDB::class.java,
        context.getString(R.string.app_name)
    )
        .allowMainThreadQueries()
        .build()

}