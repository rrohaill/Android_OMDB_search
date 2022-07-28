package io.rohail.androidomdbsearch.ui.search.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Error") val message: String,
    @SerializedName("Response") val isSuccess: String
)
