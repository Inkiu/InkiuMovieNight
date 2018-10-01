package com.example.hwanginkiu.inkiumovienight.presentation.populars

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.common.ImageLoader
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie
import kotlinx.android.synthetic.main.popular_movies_adapter_cell.view.*

class PopularMoviesAdapter(
        private val imageLoader: ImageLoader,
        private val onMovieSelected: (Movie, View) -> Unit
) : RecyclerView.Adapter<PopularMoviesAdapter.MovieCellViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movies_adapter_cell,
                parent,
                false
        )
        return MovieCellViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieCellViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.itemView) {
            title.text = movie.originalTitle
            movie.posterPath?.let { imageLoader.load(it, image) }
            setOnClickListener { onMovieSelected(movie, this) }
        }
    }

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class MovieCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}