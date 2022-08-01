package io.rohail.androidomdbsearch.ui.detail.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.androidomdbsearch.ui.detail.usecase.SearchFilterUseCase
import io.rohail.androidomdbsearch.ui.favourite.usecase.FavouriteUseCase
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val searchFilterUseCase: SearchFilterUseCase
) : ViewModel() {

    fun addFavourite(favourite: DetailResponse) {
        favouriteUseCase.insertFavourite(favourite)
    }

    fun deleteFavourite(id: String) {
        favouriteUseCase.deleteFavourite(id = id)
    }

    fun containsFavourite(id:String):Boolean= favouriteUseCase.containsFavourite(id = id)

    fun containsFilter(id: String): Boolean = searchFilterUseCase.getAllFilters().contains(id)

    fun addSearchFilter(id: String) = searchFilterUseCase.insertFilter(id = id)

    fun removeSearchFilter(id: String) = searchFilterUseCase.removeFilter(id = id)

}