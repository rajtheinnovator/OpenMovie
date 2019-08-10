package me.abhishekraj.openmovie.ui.moviedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.data.MoviesRepositoryWithoutPagedList
import me.abhishekraj.openmovie.data.model.MovieDetail
import me.abhishekraj.openmovie.utils.UIState

/**
 * Created by Abhishek Raj on 6/24/2019.
 */

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchMovieDetails(movieId: String): LiveData<MovieDetail>? {
        MoviesRepositoryWithoutPagedList.getInstance(getApplication()).getMovieDetails(movieId)
        return movieDetail
    }

    var movieDetail: LiveData<MovieDetail>? = null
        get() = field ?: MoviesRepositoryWithoutPagedList.getInstance(getApplication()).movieDetails

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