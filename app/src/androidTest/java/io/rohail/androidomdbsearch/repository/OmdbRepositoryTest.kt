package io.rohail.androidomdbsearch.repository

import androidx.test.platform.app.InstrumentationRegistry
import io.rohail.androidomdbsearch.base.api.ApiResult
import io.rohail.androidomdbsearch.ui.search.data.OMDBDataSource
import io.rohail.androidomdbsearch.ui.search.data.OMDBDataSourceImpl
import io.rohail.metaweatherapp.api.ApiFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

class OmdbRepositoryTest {

    @Test
    fun testSearchDatabase() = runBlocking {
        val result = getDataSource().searchDatabase("Marvel")

        assert(result is ApiResult.Success && !result.data.search.isNullOrEmpty())
    }

    @Test
    fun testFetchDetail() = runBlocking {
        val result = getDataSource().getDetail(id = "tt4154664")

        assert(result is ApiResult.Success && result.data.isSuccess == "True")
    }

    private fun getDataSource(): OMDBDataSource = OMDBDataSourceImpl(
        ApiFactory.getApi(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
    )

}