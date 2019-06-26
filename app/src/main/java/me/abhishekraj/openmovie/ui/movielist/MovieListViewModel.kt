package me.abhishekraj.openmovie.ui.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchMovies(type: String): LiveData<PagedList<Movie>?>? {
        return MoviesRepository(getApplication()).getLiveDataOfPagedList(type)
    }

    var chosenMovie: Movie? = null
}
