package io.rohail.androidomdbsearch.ui

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class SearchFilter(@PrimaryKey val imdbID: String)
