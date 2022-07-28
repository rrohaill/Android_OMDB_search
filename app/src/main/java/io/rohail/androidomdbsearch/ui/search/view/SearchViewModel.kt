package io.rohail.androidomdbsearch.ui.search.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.androidomdbsearch.ui.search.model.DetailResult
import io.rohail.androidomdbsearch.ui.search.model.SearchResult
import io.rohail.androidomdbsearch.ui.search.usecase.FetchDetailUseCase
import io.rohail.androidomdbsearch.ui.search.usecase.GetDetailResultUseCase
import io.rohail.androidomdbsearch.ui.search.usecase.GetSearchResultUseCase
import io.rohail.androidomdbsearch.ui.search.usecase.SearchDatabaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchDatabaseUseCase: SearchDatabaseUseCase,
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val fetchDetailUseCase: FetchDetailUseCase,
    private val getDetailResultUseCase: GetDetailResultUseCase
) :
    ViewModel() {

    fun searchDatabase(searchKey: String) {
        viewModelScope.launch {
            searchDatabaseUseCase(searchKey = searchKey)
        }
    }

    fun getSearchResult(): Flow<SearchResult> = getSearchResultUseCase()

    fun fetchDetail(id: String) {
        viewModelScope.launch {
            fetchDetailUseCase(id = id)
        }
    }

    fun getDetailResult(): Flow<DetailResult> = getDetailResultUseCase()
}