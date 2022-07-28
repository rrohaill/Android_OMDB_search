package io.rohail.metaweatherapp.api

import android.content.Context
import io.rohail.androidomdbsearch.base.api.OmdbApi
import io.rohail.androidomdbsearch.base.api.RetrofitFactory
import retrofit2.create

object ApiFactory {
    private lateinit var omdbApi: OmdbApi

    private const val API_BASE_URL = "http://www.omdbapi.com/"

    fun getApi(context: Context): OmdbApi {
        if (!ApiFactory::omdbApi.isInitialized) {
            omdbApi = RetrofitFactory.retrofit(
                baseUrl = API_BASE_URL,
                context = context
            ).create()
        }
        return omdbApi
    }
}