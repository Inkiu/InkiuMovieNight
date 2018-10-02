package com.example.hwanginkiu.inkiumovienight.presentation.populars

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.common.App
import com.example.hwanginkiu.inkiumovienight.presentation.common.BaseFragment
import com.example.hwanginkiu.inkiumovienight.presentation.common.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import org.koin.android.ext.android.inject
import javax.inject.Inject

class PopularMoviesFragment : BaseFragment() {

    val factory: PopularMoviesVMFactory by inject()
    val imageLoader: ImageLoader by inject()

    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(PopularMoviesViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getPopularMovies()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let { Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show() }
            throwable?.printStackTrace()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = popular_movies_progress
        popularMoviesAdapter = PopularMoviesAdapter(imageLoader) { movie, view ->
            navigateToMovieDetailsScreen(movie, view)
        }
        recyclerView = popular_movies_recyclerview
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = popularMoviesAdapter

    }

    private fun handleViewState(viewState: PopularMoviesViewState) {
        progressBar.visibility = if (viewState.showLoading) View.VISIBLE else View.GONE
        popularMoviesAdapter.addMovies(viewState.movies)
    }
}