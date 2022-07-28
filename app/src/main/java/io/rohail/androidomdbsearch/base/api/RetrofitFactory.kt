package io.rohail.androidomdbsearch.base.api

import android.content.Context
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun buildOkHttpClient(
        context: Context
    ): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        //Log request & response headers/body if debug
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    fun retrofit(
        baseUrl: String,
        context: Context,
    ): Retrofit {

        retrofit = Retrofit.Builder()
            .client(buildOkHttpClient(context))
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    var retrofit: Retrofit? = null
}