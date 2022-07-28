package io.rohail.androidomdbsearch.ui.search.model

import io.rohail.androidomdbsearch.base.api.ApiError
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

sealed class DetailResult {
    data class Success(val data: DetailResponse) : DetailResult()
    data class Error(val error: ApiError) : DetailResult()
}
