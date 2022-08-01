package io.rohail.androidomdbsearch.ui.favourite.usecase

import io.rohail.androidomdbsearch.base.database.FavouriteDao
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavouriteUseCase {
    fun insertFavourite(favourite: DetailResponse)
    fun containsFavourite(id: String): Boolean
    fun getAllFavouritesFlow(): Flow<List<DetailResponse>>
    fun deleteFavourite(id: String)
}

class FavouriteUseCaseImpl @Inject constructor(private val favouriteDao: FavouriteDao) :
    FavouriteUseCase {

    override fun insertFavourite(favourite: DetailResponse) {
        favouriteDao.insertFavourite(favourite = favourite)
    }

    override fun containsFavourite(id: String): Boolean =
        favouriteDao.containsFavourite(id = id)

    override fun getAllFavouritesFlow(): Flow<List<DetailResponse>> =
        favouriteDao.getAllFavouritesFlow()

    override fun deleteFavourite(id: String) {
        favouriteDao.deleteFavourite(id = id)
    }

}