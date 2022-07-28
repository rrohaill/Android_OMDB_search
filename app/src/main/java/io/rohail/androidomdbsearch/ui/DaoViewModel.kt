package io.rohail.androidomdbsearch.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.androidomdbsearch.base.database.FavouriteDao
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DaoViewModel @Inject constructor(private val detailDao: FavouriteDao) : ViewModel() {

    fun getAllFavourites(): Flow<List<DetailResponse>> = detailDao.getAllFavourites()

    fun addFavourite(favourite: DetailResponse) {
        detailDao.insertFavourite(favourite)
    }

    fun deleteFavourite(id: String) {
        detailDao.deleteFavourite(id = id)
    }

}