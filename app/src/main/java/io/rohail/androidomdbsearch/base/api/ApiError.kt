package io.rohail.androidomdbsearch.base.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiError(
    @SerializedName("Response") val response: String = "",
    @SerializedName("Error") val message: String = ""
)