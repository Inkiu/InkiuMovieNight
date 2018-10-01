package com.example.hwanginkiu.inkiumovienight.presentation.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Slide
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import co.lujun.androidtagview.TagContainerLayout
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.common.App
import com.example.hwanginkiu.inkiumovienight.presentation.common.ImageLoader
import com.example.hwanginkiu.inkiumovienight.presentation.common.SimpleTransitionEndedCallback
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Video
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.details_overview_section.*
import kotlinx.android.synthetic.main.details_video_section.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    @Inject lateinit var factory: MovieDetailVMFactory
    @Inject lateinit var imageLoader: ImageLoader

    private lateinit var detailViewModel: MovieDetailViewModel
    private lateinit var backdropImage: ImageView
    private lateinit var posterImage: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var videos: RecyclerView
    private lateinit var videosSection: View
    private lateinit var backButton: View
    private lateinit var tagsContainer: TagContainerLayout
    private lateinit var favoriteButton: FloatingActionButton

    companion object {
        private const val MOVIE_ID: String = "extra_movie_id"
        private const val MOVIE_POSTER_URL: String = "extra_movie_poster_url"

        fun newIntent(context: Context, movieId: Int, posterUrl: String?): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
                putExtra(MOVIE_POSTER_URL, posterUrl)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        postponeEnterTransition()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_FULLSCREEN

        (application as App).createDetailComponent().inject(this)

        factory.movieId = intent.getIntExtra(MOVIE_ID, 0)
        detailViewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)
        backButton = details_back_button
        backButton.setOnClickListener { finish() }
        favoriteButton = details_favorite_fab
        favoriteButton.setOnClickListener { detailViewModel.favoriteButtonClicked() }
        backdropImage = details_backdrop
        posterImage = details_poster
        title = details_title
        overview = details_overview
        releaseDate = details_release_date
        tagsContainer = details_tags
        score = details_score
        videos = details_videos
        videosSection = details_video_section

        intent.getStringExtra(MOVIE_POSTER_URL)?.let { posterUrl ->
            imageLoader.load(posterUrl, posterImage) {
                startPostponedEnterTransition()
            }
        } ?: startPostponedEnterTransition()

        window.sharedElementEnterTransition.addListener(SimpleTransitionEndedCallback{
            observeViewState()
        })

        // If we don't have any entering transition
        if (savedInstanceState != null) {
            observeViewState()
        } else {
            detailViewModel.getMovieDetails()
        }
    }

    private fun observeViewState() {
        detailViewModel.viewState.observe(this, Observer { handleViewState(it) })
        detailViewModel.favoriteState.observe(this, Observer { handleFavoriteState(it) })
        detailViewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: MovieDetailsViewState?) {
        if (state == null) return

        title.text = state.title
        overview.text = state.overview
        releaseDate.text = String.format(getString(R.string.release_date_template, state.releaseDate))
        score.text = if (state.votesAverage == 0.0) getString(R.string.n_a) else state.votesAverage.toString()
        state.genres?.let { tagsContainer.tags = state.genres }

        val transition = Slide()
        transition.excludeTarget(posterImage, true)
        transition.duration = 750
        TransitionManager.beginDelayedTransition(details_root_view, transition)
        title.visibility = View.VISIBLE
        releaseDate.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        details_release_date_layout.visibility = View.VISIBLE
        details_score_layout.visibility = View.VISIBLE
        details_overview_section.visibility = View.VISIBLE
        videosSection.visibility = View.VISIBLE
        tagsContainer.visibility = View.VISIBLE

        state.backdropUrl.let { imageLoader.load(it, backdropImage) }

        state.videos.let {
            val videosAdapter = VideosAdapter(it, this::onVideoSelected)
            videos.layoutManager = LinearLayoutManager(this)
            videos.adapter = videosAdapter

        }
    }

    private fun handleFavoriteState(favorite: Boolean?) {
        if (favorite == null) return
        favoriteButton.visibility = View.VISIBLE
        favoriteButton.setImageDrawable(
                if (favorite)
                    ContextCompat.getDrawable(this, android.R.drawable.arrow_down_float)
                else
                    ContextCompat.getDrawable(this, android.R.drawable.arrow_up_float))
    }

    private fun onVideoSelected(video: Video) {
        video.url.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as App).releasePopularComponent()
    }
}
