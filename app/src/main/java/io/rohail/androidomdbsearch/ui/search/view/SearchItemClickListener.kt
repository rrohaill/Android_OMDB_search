package io.rohail.androidomdbsearch.ui.search.view

import io.rohail.androidomdbsearch.ui.search.data.model.Search

interface SearchItemClickListener {
    fun onItemClicked(item: Search)
}