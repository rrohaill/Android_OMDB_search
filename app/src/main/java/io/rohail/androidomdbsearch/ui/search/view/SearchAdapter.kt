package io.rohail.androidomdbsearch.ui.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.rohail.androidomdbsearch.databinding.SearchItemBinding
import io.rohail.androidomdbsearch.ui.search.data.model.Search

class SearchAdapter(
    private val searchItems: MutableList<Search>,
    private val searchItemClickListener: SearchItemClickListener
) :
    RecyclerView.Adapter<SearchItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItemViewHolder(binding, searchItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        searchItems.getOrNull(position)?.let {
            holder.bindView(searchItem = it)
        }
    }

    override fun getItemCount() = searchItems.size

    fun addItems(newSearchItems: List<Search>?) {
        newSearchItems?.let {
            searchItems.addAll(newSearchItems)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        searchItems.clear()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Search {
        return searchItems[position]
    }
}