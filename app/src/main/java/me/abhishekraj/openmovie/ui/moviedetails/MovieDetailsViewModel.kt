package me.abhishekraj.openmovie.ui.moviedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.data.MoviesRepository
import me.abhishekraj.openmovie.data.model.MovieDetail
import me.abhishekraj.openmovie.utils.UIState
import javax.inject.Inject

/**
 * Created by Abhishek Raj on 6/24/2019.
 */

class MovieDetailsViewModel @Inject constructor(
    application: Application,
    private val moviesRepository: MoviesRepository
) :
    AndroidViewModel(application) {

    fun fetchMovieDetails(movieId: String): LiveData<MovieDetail>? {
        moviesRepository.getMovieDetails(movieId)
        return movieDetail
    }

    var movieDetail: LiveData<MovieDetail>? = null
        get() = field ?: moviesRepository.movieDetails

    private val _uiState: MutableLiveData<UIState> = MutableLiveData()
    val uiState: LiveData<UIState>
        get() = _uiState

    fun setUiState(newState: UIState) {
        _uiState.value = newState
    }

    init {
        _uiState.value = UIState.LOADING
    }
}