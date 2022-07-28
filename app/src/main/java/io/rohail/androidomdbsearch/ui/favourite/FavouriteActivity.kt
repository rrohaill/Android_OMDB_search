package io.rohail.androidomdbsearch.ui.favourite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.androidomdbsearch.databinding.ActivityFavouriteBinding
import io.rohail.androidomdbsearch.ui.detail.DetailActivity
import io.rohail.androidomdbsearch.ui.DaoViewModel
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity(), FavouriteItemClickListener {

    private lateinit var binding: ActivityFavouriteBinding
    private val viewModel: DaoViewModel by viewModels()
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        collectData()
    }

    private fun initView() {
        favouriteAdapter = FavouriteAdapter(mutableListOf(), this)
        binding.recyclerView.apply {
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(this@FavouriteActivity)
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.getAllFavourites().collectLatest {
                favouriteAdapter.clear()
                favouriteAdapter.addItems(it)
            }
        }
    }

    override fun onItemClicked(item: DetailResponse) {
        startActivity(DetailActivity.newInstance(this, item, true))
    }
}