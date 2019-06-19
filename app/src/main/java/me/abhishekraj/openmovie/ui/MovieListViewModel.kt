package me.abhishekraj.openmovie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.utils.thereIsConnection

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    var movieType: MutableLiveData<String>? = null

    fun fetchMovies(type: String) {
        if (thereIsConnection(getApplication())) {
            movieList = MoviesRepository(getApplication()).getLiveDataOfPagedList(type)
        } else {
            MoviesRepository(getApplication()).getListOfMovies(movieType)
        }

        MoviesRepository(getApplication()).getListOfMovies(movieType)
    }

    var movieList: LiveData<PagedList<Movie>?>? = null
        get() = field ?: MoviesRepository(getApplication()).movies.also {
            field = it
            MoviesRepository(getApplication()).getListOfMovies(movieType)
        }
}
