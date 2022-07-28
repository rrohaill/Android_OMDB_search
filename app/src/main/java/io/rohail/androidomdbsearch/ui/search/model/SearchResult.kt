package io.rohail.androidomdbsearch.ui.search.model

import io.rohail.androidomdbsearch.base.api.ApiError
import io.rohail.androidomdbsearch.ui.search.data.model.Search

sealed class SearchResult {
    data class Success(val data: List<Search>) : SearchResult()
    data class Error(val error: ApiError) : SearchResult()
}
