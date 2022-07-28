package io.rohail.androidomdbsearch.base.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternetConnection()) {
            throw IOException("No internet connection.")
        }
        return chain.proceed(chain.request())
    }

    private fun hasInternetConnection(): Boolean = check()

    private fun check(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.let {
            it.getNetworkCapabilities(cm.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                ) {
                    return true
                }
            }
        }
        return false
    }

}