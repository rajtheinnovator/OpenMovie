package me.abhishekraj.openmovie.ui.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.Resource
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.MovieList
import javax.inject.Inject

class MovieListViewModel @Inject constructor(application: Application, private val moviesRepository: MoviesRepository) :
    AndroidViewModel(application) {

    private val moviesList: LiveData<List<Movie>>?
        get() = moviesRepository.liveDataOfListOfMovie

    fun fetchMovies(type: String): LiveData<List<Movie>>? {
        moviesRepository.fetchLiveDataOfMovieList(type)
        return moviesList
    }

    fun fetchMoviesResource(type: String): LiveData<Resource<MovieList>> {
        return moviesRepository.loadListOfMovies(type)
    }

    fun getAllPlantsData(){
        moviesRepository.getPlantDetails()
    }

    var chosenMovie: Movie? = null
}