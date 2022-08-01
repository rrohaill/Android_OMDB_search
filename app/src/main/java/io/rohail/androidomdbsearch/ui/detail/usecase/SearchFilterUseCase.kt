package io.rohail.androidomdbsearch.ui.detail.usecase

import io.rohail.androidomdbsearch.base.database.FilterDao
import io.rohail.androidomdbsearch.ui.SearchFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface SearchFilterUseCase {
    fun insertFilter(id: String)
    fun removeFilter(id: String)
    fun getAllFilters(): List<String>
    fun getAllFiltersFlow(): Flow<List<String>>
}

class SearchFilterUseCaseImpl @Inject constructor(private val filterDao: FilterDao) :
    SearchFilterUseCase {

    override fun insertFilter(id: String) {
        filterDao.insertFilter(id = SearchFilter(imdbID = id))
    }

    override fun getAllFilters(): List<String> = filterDao.getAllFilters().map { it.imdbID }

    override fun getAllFiltersFlow(): Flow<List<String>> =
        filterDao.getAllFiltersFlow().map { list -> list.map { it.imdbID } }

    override fun removeFilter(id: String) {
        filterDao.deleteFilter(id = id)
    }
}