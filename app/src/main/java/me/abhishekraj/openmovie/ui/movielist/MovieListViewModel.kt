package me.abhishekraj.openmovie.ui.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.MoviesRepositoryWithoutPagedList
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {


    val moviesList: LiveData<List<Movie>>?
        get() = MoviesRepositoryWithoutPagedList.getInstance(getApplication()).moviesListLiveData

    fun fetchMovies(type: String): LiveData<List<Movie>>? {
        MoviesRepositoryWithoutPagedList.getInstance(getApplication()).fetchLiveDataOfMovieList(type)
        return moviesList
    }

    var chosenMovie: Movie? = null
}
