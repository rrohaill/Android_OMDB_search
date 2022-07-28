package io.rohail.androidomdbsearch.ui.search.usecase

import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import javax.inject.Inject

interface SearchDatabaseUseCase {
    suspend operator fun invoke(searchKey: String)
}

class SearchDatabaseUseCaseImpl @Inject constructor( private val omdbRepository: OMDBRepository) :
    SearchDatabaseUseCase {
    override suspend fun invoke(searchKey: String) {
        omdbRepository.searchDatabase(searchKey = searchKey)
    }

}