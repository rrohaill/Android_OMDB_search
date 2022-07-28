package io.rohail.androidomdbsearch.ui.favourite

import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

interface FavouriteItemClickListener {
    fun onItemClicked(item: DetailResponse)
}