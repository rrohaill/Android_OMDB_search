package io.rohail.androidomdbsearch.ui.favourite.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.androidomdbsearch.ui.favourite.usecase.FavouriteUseCase
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val favouriteUseCase: FavouriteUseCase) :
    ViewModel() {

    fun getAllFavourites(): Flow<List<DetailResponse>> = favouriteUseCase.getAllFavouritesFlow()

}