package io.rohail.androidomdbsearch.ui.search.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search") val search: List<Search>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Error") val message: String?,
    @SerializedName("Response") val isSuccess: String
)

data class Search(
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Year") val year: String
)
