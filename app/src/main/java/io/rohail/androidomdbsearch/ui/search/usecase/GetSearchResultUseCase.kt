package io.rohail.androidomdbsearch.ui.search.usecase

import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import io.rohail.androidomdbsearch.ui.search.model.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSearchResultUseCase {
    operator fun invoke(): Flow<SearchResult>
}

class GetSearchResultUseCaseImpl @Inject constructor(private val omdbRepository: OMDBRepository) :
    GetSearchResultUseCase {

    override fun invoke(): Flow<SearchResult> = omdbRepository.getSearchResult()

}