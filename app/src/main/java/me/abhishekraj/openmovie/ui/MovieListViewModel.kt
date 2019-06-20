package me.abhishekraj.openmovie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchMovies(type: String): LiveData<PagedList<Movie>?>? {

        movieList = MoviesRepository(getApplication()).getLiveDataOfPagedList(type)
        return movieList
    }

    var movieList: LiveData<PagedList<Movie>?>? = null
        get() = field ?: MoviesRepository(getApplication()).movies.also {
            field = it
        }
}
