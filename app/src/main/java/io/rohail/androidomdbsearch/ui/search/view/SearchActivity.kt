package io.rohail.androidomdbsearch.ui.search.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.androidomdbsearch.databinding.ActivitySearchBinding
import io.rohail.androidomdbsearch.ui.detail.view.DetailActivity.Companion.newInstance
import io.rohail.androidomdbsearch.ui.favourite.view.FavouriteActivity
import io.rohail.androidomdbsearch.ui.search.data.model.Search
import io.rohail.androidomdbsearch.ui.search.model.DetailResult
import io.rohail.androidomdbsearch.ui.search.model.SearchResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    SearchItemClickListener, TextView.OnEditorActionListener {

    private lateinit var binding: ActivitySearchBinding

    private lateinit var adapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        collectResults()
    }

    private fun initView() {
        binding.apply {
            swipeRefresh.setOnRefreshListener(this@SearchActivity)
            searchButton.setOnClickListener {
                hideKeyboard()
                clearMovieList()
                makeOMDBCall()
            }
            searchInput.setOnEditorActionListener(this@SearchActivity)
            fab.setOnClickListener {
                startActivity((Intent(this@SearchActivity, FavouriteActivity::class.java)))
            }
            adapter = SearchAdapter(mutableListOf(), this@SearchActivity)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    private fun hideKeyboard() {
        binding.searchInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun collectResults() {
        lifecycleScope.launch {
            viewModel.getSearchResult().collectLatest {
                binding.swipeRefresh.isRefreshing = false
                when (it) {
                    is SearchResult.Success -> showData(it.data)
                    is SearchResult.Error -> showError(it.error.message)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getDetailResult().collectLatest {
                binding.swipeRefresh.isRefreshing = false
                when (it) {
                    is DetailResult.Success -> startActivity(
                        newInstance(
                            context = this@SearchActivity,
                            it.data
                        )
                    )
                    is DetailResult.Error -> showError(it.error.message)
                }
            }
        }
    }

    private fun showData(data: List<Search>) {
        adapter.clear()
        val filteredResult = viewModel.getFilteredData(data)
        binding.noResultMessage.visibility = View.GONE
        adapter.addItems(filteredResult)
    }

    private fun showError(message: String) {
        clearMovieList()
        binding.noResultMessage.apply {
            visibility = View.VISIBLE
            text = message
        }
    }


    override fun onRefresh() {
        if (getUserSearchKey().isNotEmpty()) {
            clearMovieList()
            makeOMDBCall()
        } else {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun makeOMDBCall() {
        viewModel.searchDatabase(getUserSearchKey())
    }

    private fun clearMovieList() {
        adapter.clear()
    }

    private fun getUserSearchKey(): String {
        return (binding.searchInput.text?.trim() ?: "").toString()
    }

    override fun onItemClicked(item: Search) {
        binding.swipeRefresh.isRefreshing = true
        viewModel.fetchDetail(item.imdbID)
    }

    override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            hideKeyboard()
            clearMovieList()
            makeOMDBCall()
            return true
        }
        return false
    }
}