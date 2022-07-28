package io.rohail.androidomdbsearch.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.rohail.androidomdbsearch.databinding.SearchItemBinding
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

class FavouriteAdapter(
    private val searchItems: MutableList<DetailResponse>,
    private val searchItemClickListener: FavouriteItemClickListener
) :
    RecyclerView.Adapter<FavouriteItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteItemViewHolder(binding, searchItemClickListener)
    }

    override fun onBindViewHolder(holder: FavouriteItemViewHolder, position: Int) {
        searchItems.getOrNull(position)?.let {
            holder.bindView(item = it)
        }
    }

    override fun getItemCount() = searchItems.size

    fun addItems(newSearchItems: List<DetailResponse>?) {
        newSearchItems?.let {
            searchItems.addAll(newSearchItems)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        searchItems.clear()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): DetailResponse {
        return searchItems[position]
    }
}