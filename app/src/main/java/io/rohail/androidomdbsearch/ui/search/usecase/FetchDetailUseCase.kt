package io.rohail.androidomdbsearch.ui.search.usecase

import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import javax.inject.Inject

interface FetchDetailUseCase {
    suspend operator fun invoke(id: String)
}

class FetchDetailUseCaseImpl @Inject constructor(private val omdbRepository: OMDBRepository) :
    FetchDetailUseCase {
    override suspend fun invoke(id: String) {
        omdbRepository.fetchDetail(id = id)
    }

}