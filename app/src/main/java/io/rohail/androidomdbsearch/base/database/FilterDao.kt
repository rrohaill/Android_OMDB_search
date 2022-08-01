package io.rohail.androidomdbsearch.base.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.rohail.androidomdbsearch.ui.SearchFilter
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface FilterDao {

    @Insert(onConflict = REPLACE)
    fun insertFilter(id: SearchFilter)

    @Query("SELECT * FROM SearchFilter")
    fun getAllFilters(): List<SearchFilter>

    @Query("SELECT * FROM SearchFilter")
    fun getAllFiltersFlow(): Flow<List<SearchFilter>>

    fun getAllFiltersDistinct(): Flow<List<SearchFilter>> =
        getAllFiltersFlow().distinctUntilChanged()

    @Query("DELETE FROM SearchFilter WHERE imdbID = :id")
    fun deleteFilter(id: String)
}