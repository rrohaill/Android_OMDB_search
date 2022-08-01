package io.rohail.androidomdbsearch.ui.search.usecase

import io.rohail.androidomdbsearch.ui.detail.usecase.SearchFilterUseCase
import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import io.rohail.androidomdbsearch.ui.search.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface GetSearchResultUseCase {
    operator fun invoke(): Flow<SearchResult>
}

class GetSearchResultUseCaseImpl @Inject constructor(
    private val omdbRepository: OMDBRepository, private val searchFilterUseCase: SearchFilterUseCase
) :
    GetSearchResultUseCase {

    override fun invoke(): Flow<SearchResult> =
        searchFilterUseCase.getAllFiltersFlow()
            .combine(omdbRepository.getSearchResult()) { filter, result ->
                if (result is SearchResult.Success) {
                    val filteredData = result.data.filter { !filter.contains(it.imdbID) }
                    SearchResult.Success(data = filteredData)
                } else
                    result
            }

}