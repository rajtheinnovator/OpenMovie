package me.abhishekraj.openmovie.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.utils.thereIsConnection

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class MoviesRepository(val application: Application) {

    /*
    We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer
    */

    private val _movies = MutableLiveData<List<Movie>?>()
    val movies: LiveData<List<Movie>?>
        get() = _movies

    fun getListOfMovies(movieType: MutableLiveData<String>?) {
        if (thereIsConnection(application)) {
            fetchDataFromApi(movieType)
        } else {
            fetchDataFromLocalDatabase(movieType)
        }
    }

    private fun fetchDataFromLocalDatabase(movieType: MutableLiveData<String>?) {
        //TODO("not implemented")
    }

    private fun fetchDataFromApi(movieType: MutableLiveData<String>?) {
        //TODO("not implemented")
    }

    companion object {
        private const val TAG = "MoviesRepository"
    }

}