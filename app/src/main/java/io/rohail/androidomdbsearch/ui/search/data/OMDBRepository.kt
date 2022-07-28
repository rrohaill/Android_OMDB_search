package io.rohail.androidomdbsearch.ui.search.data

import io.rohail.androidomdbsearch.base.api.ApiError
import io.rohail.androidomdbsearch.base.api.ApiResult
import io.rohail.androidomdbsearch.ui.search.model.DetailResult
import io.rohail.androidomdbsearch.ui.search.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

interface OMDBRepository {
    suspend fun searchDatabase(searchKey: String)
    suspend fun fetchDetail(id: String)

    fun getSearchResult(): Flow<SearchResult>
    fun getDetailResult(): Flow<DetailResult>
}

class OMDBRepositoryImpl @Inject constructor(private val omdbDataSource: OMDBDataSource) :
    OMDBRepository {

    private val searchResultFlow = MutableSharedFlow<SearchResult>()
    private val detailFlow = MutableSharedFlow<DetailResult>()

    override suspend fun searchDatabase(searchKey: String) {
        omdbDataSource.searchDatabase(searchKey = searchKey).let {
            when (it) {
                is ApiResult.Success ->
                    if (it.data.isSuccess == "True")
                        searchResultFlow.emit(
                            SearchResult.Success(
                                data = it.data.search ?: emptyList()
                            )
                        )
                    else
                        searchResultFlow.emit(
                            SearchResult.Error(
                                error = ApiError(
                                    message = it.data.message ?: "No result found"
                                )
                            )
                        )
                is ApiResult.Error -> searchResultFlow.emit(SearchResult.Error(error = it.error))
            }
        }
    }

    override suspend fun fetchDetail(id: String) {
        omdbDataSource.getDetail(id = id).let {
            when (it) {
                is ApiResult.Success ->
                    if (it.data.isSuccess == "True")
                        detailFlow.emit(
                            DetailResult.Success(
                                data = it.data
                            )
                        )
                    else
                        detailFlow.emit(
                            DetailResult.Error(
                                error = ApiError(
                                    message = it.data.message ?: "No result found"
                                )
                            )
                        )
                is ApiResult.Error -> detailFlow.emit(DetailResult.Error(error = it.error))
            }
        }
    }

    override fun getSearchResult(): Flow<SearchResult> = searchResultFlow

    override fun getDetailResult(): Flow<DetailResult> = detailFlow

}