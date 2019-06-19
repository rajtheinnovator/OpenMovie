package me.abhishekraj.openmovie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    var movieType: MutableLiveData<String>? = null

    fun fetchMovies(type: String) {
        movieType?.value = type
        MoviesRepository(getApplication()).getListOfMovies(movieType)
    }

    var movieList: LiveData<List<Movie>?>? = null
        get() = field ?: MoviesRepository(getApplication()).movies.also {
            field = it
            MoviesRepository(getApplication()).getListOfMovies(movieType)
        }
}
