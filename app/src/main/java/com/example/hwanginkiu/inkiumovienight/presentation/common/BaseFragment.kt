package com.example.hwanginkiu.inkiumovienight.presentation.common

import android.app.ActivityOptions
import android.support.v4.app.Fragment
import android.view.View
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailActivity
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie

open class BaseFragment : Fragment() {

    protected fun navigateToMovieDetailsScreen(movie: Movie, view: View) {
        var activityOptions: ActivityOptions? = null

        val imageForTransition: View? = view.findViewById(R.id.image)
        imageForTransition?.let {
            val posterSharedElement = android.util.Pair.create(it,  getString(R.string.transition_poster))
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, posterSharedElement)
        }
        // starting act
        startActivity(MovieDetailActivity.newIntent(
                context!!,
                movie.id,
                movie.posterPath), activityOptions?.toBundle())

        activity?.overridePendingTransition(0, 0)
    }
}