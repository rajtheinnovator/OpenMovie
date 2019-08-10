package me.abhishekraj.openmovie.ui.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.Resource
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {


    val moviesList: LiveData<List<Movie>>?
        get() = MoviesRepository.getInstance(getApplication()).moviesListLiveData

    fun fetchMovies(type: String): LiveData<List<Movie>>? {
        MoviesRepository.getInstance(getApplication()).fetchLiveDataOfMovieList(type)
        return moviesList
    }


    fun fetchMoviesResource(type: String): LiveData<Resource<List<Movie>>> {
        return MoviesRepository.getInstance(getApplication()).loadListOfMovies(type)
    }

    var chosenMovie: Movie? = null
}
