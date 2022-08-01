package io.rohail.androidomdbsearch.base.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface FavouriteDao {

    @Insert(onConflict = REPLACE)
    fun insertFavourite(favourite: DetailResponse)

    @Query("SELECT * FROM DetailResponse")
    fun getAllFavourites(): List<DetailResponse>

    @Query("SELECT * FROM DetailResponse")
    fun getAllFavouritesFlow(): Flow<List<DetailResponse>>

    fun getAllFavouritesDistinct(): Flow<List<DetailResponse>> =
        getAllFavouritesFlow().distinctUntilChanged()

    fun containsFavourite(id: String): Boolean =
        getAllFavourites().map { it.imdbID == id }.isNotEmpty()

    @Query("DELETE FROM DetailResponse WHERE imdbID = :id")
    fun deleteFavourite(id: String)
}