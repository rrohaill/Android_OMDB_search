package io.rohail.androidomdbsearch.ui.favourite.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.rohail.androidomdbsearch.R
import io.rohail.androidomdbsearch.databinding.SearchItemBinding
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

class FavouriteItemViewHolder(
    private val binding: SearchItemBinding,
    private val favouriteItemClickListener: FavouriteItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: DetailResponse) {
        binding.apply {
            tvTitle.text = item.title
            tvType.text = item.type
            tvYear.text = item.year
            if (item.poster.isNotEmpty()) {
                Glide.with(root.context).load(item.poster)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_movie)
                    .into(binding.ivIcon)
            }
            itemView.setOnClickListener {
                favouriteItemClickListener.onItemClicked(item)
            }
        }
    }
}