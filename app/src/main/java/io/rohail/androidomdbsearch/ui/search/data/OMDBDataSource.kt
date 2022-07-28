package io.rohail.androidomdbsearch.ui.search.data

import io.rohail.androidomdbsearch.BuildConfig
import io.rohail.androidomdbsearch.base.api.ApiResult
import io.rohail.androidomdbsearch.base.api.BaseDataSource
import io.rohail.androidomdbsearch.base.api.OmdbApi
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import io.rohail.androidomdbsearch.ui.search.data.model.SearchResponse
import javax.inject.Inject

interface OMDBDataSource {
    suspend fun searchDatabase(
        searchKey: String
    ): ApiResult<SearchResponse>

    suspend fun getDetail(
        id: String
    ): ApiResult<DetailResponse>
}

class OMDBDataSourceImpl @Inject constructor(private val service: OmdbApi) : OMDBDataSource,
    BaseDataSource() {
    override suspend fun searchDatabase(searchKey: String): ApiResult<SearchResponse> = getResult {
        service.searchMovies(apikey = BuildConfig.OMDB_API_KEY, searchKey = searchKey)
    }

    override suspend fun getDetail(id: String): ApiResult<DetailResponse> = getResult {
        service.getDetail(apikey = BuildConfig.OMDB_API_KEY, id = id)
    }

}