package io.rohail.androidomdbsearch.base.api

import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import io.rohail.androidomdbsearch.ui.search.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("?type=movie")
    suspend fun searchMovies(
        @Query("apikey") apikey: String,
        @Query("s") searchKey: String
    ): Response<SearchResponse>

    @GET(".")
    suspend fun getDetail(
        @Query("apikey") apikey: String,
        @Query("i") id: String
    ): Response<DetailResponse>

}