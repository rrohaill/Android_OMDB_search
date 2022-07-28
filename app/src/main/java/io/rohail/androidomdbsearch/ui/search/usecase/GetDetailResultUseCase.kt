package io.rohail.androidomdbsearch.ui.search.usecase

import io.rohail.androidomdbsearch.ui.search.data.OMDBRepository
import io.rohail.androidomdbsearch.ui.search.model.DetailResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetDetailResultUseCase {
    operator fun invoke(): Flow<DetailResult>
}

class GetDetailResultUseCaseImpl @Inject constructor(private val omdbRepository: OMDBRepository) :
    GetDetailResultUseCase {

    override fun invoke(): Flow<DetailResult> = omdbRepository.getDetailResult()

}