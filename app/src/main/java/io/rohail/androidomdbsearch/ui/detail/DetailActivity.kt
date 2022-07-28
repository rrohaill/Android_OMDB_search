package io.rohail.androidomdbsearch.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import io.rohail.androidomdbsearch.R
import io.rohail.androidomdbsearch.databinding.ActivityDetailBinding
import io.rohail.androidomdbsearch.ui.DaoViewModel
import io.rohail.androidomdbsearch.ui.search.data.model.DetailResponse

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: DetailResponse
    private val viewModel: DaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = intent.getSerializableExtra(INTENT_DATA) as DetailResponse

        initView()
    }

    private fun initView() {
        binding.apply {
            Glide.with(root.context).load(data.poster).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_movie)
                .into(binding.ivIcon)

            tvTitle.text = data.title
            tvInfo.text = generateInfo()
            tvRating.text = generateRatings()
            tvPlot.text = data.plot
            tvDirector.text = data.director
            tvWriter.text = data.writer
            tvStarring.text = data.actors


            initButton()
        }
    }

    private fun initButton() {
        binding.btnFavourite.apply {
            val isFavourite = intent.getBooleanExtra(INTENT_FAVOURITE, false)
            if (isFavourite)
                text = context.getString(R.string.delete_favourite)
            setOnClickListener {
                if (isFavourite) {
                    viewModel.deleteFavourite(data.imdbID)
                    Toast.makeText(
                        this@DetailActivity,
                        "Favourite deleted.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    viewModel.addFavourite(favourite = data)
                    Toast.makeText(
                        this@DetailActivity,
                        "Added to favourite.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun generateRatings(): CharSequence {
        val ratingInOneLine = StringBuilder()
        ratingInOneLine.append(System.lineSeparator())
        data.ratings.map {
            val ratedBy = if (it.source == "Internet Movie Database") "IMDB" else it.source
            ratingInOneLine.append(ratedBy)
            ratingInOneLine.append(": ")
            ratingInOneLine.append(it.value)
            ratingInOneLine.append(System.lineSeparator())
        }
        return ratingInOneLine
    }

    private fun generateInfo(): CharSequence {
        val info = java.lang.StringBuilder()
        info.append(data.rated)
        info.append(" | ")
        info.append(data.runtime)
        info.append(" | ")
        info.append(data.genre)
        info.append(" | ")
        info.append(data.released)
        return info
    }

    companion object {
        private const val INTENT_DATA = "data"
        private const val INTENT_FAVOURITE = "favourite"
        fun newInstance(
            context: Context,
            data: DetailResponse,
            isFavouriteView: Boolean = false
        ): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(INTENT_DATA, data)
                putExtra(INTENT_FAVOURITE, isFavouriteView)
            }
    }
}