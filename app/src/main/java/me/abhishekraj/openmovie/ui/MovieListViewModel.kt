package me.abhishekraj.openmovie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    var movieList: LiveData<List<Movie>?>? = null
        get() = field ?: MoviesRepository(application = getApplication()).movies.also {
            field = it
            MoviesRepository(application = getApplication()).getListOfMovies()
        }
}
