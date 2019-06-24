package me.abhishekraj.openmovie.ui.moviedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.MovieDetail

/**
 * Created by Abhishek Raj on 6/24/2019.
 */

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchMovieDetails(movieId: String): LiveData<MovieDetail>? {
        movieDetail = MoviesRepository(getApplication()).getMovieDetails(movieId)
        return movieDetail
    }

    var movieDetail: LiveData<MovieDetail>? = null
        get() = field ?: MoviesRepository(getApplication()).movieDetails.also {
            field = it
        }
}