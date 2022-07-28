package io.rohail.androidomdbsearch.ui.search.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.rohail.androidomdbsearch.R
import io.rohail.androidomdbsearch.databinding.SearchItemBinding
import io.rohail.androidomdbsearch.ui.search.data.model.Search
import io.rohail.androidomdbsearch.ui.search.data.model.SearchResponse

class SearchItemViewHolder(
    private val binding: SearchItemBinding,
    private val searchItemClickListener: SearchItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(searchItem: Search) {
        binding.apply {
            tvTitle.text = searchItem.title
            tvType.text = searchItem.type
            tvYear.text = searchItem.year
            if (searchItem.poster.isNotEmpty()) {
                Glide.with(root.context).load(searchItem.poster)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_movie)
                    .into(binding.ivIcon)
            }
            itemView.setOnClickListener {
                searchItemClickListener.onItemClicked(searchItem)
            }
        }
    }
}