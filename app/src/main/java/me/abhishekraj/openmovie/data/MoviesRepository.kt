package me.abhishekraj.openmovie.data

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.BuildConfig
import me.abhishekraj.openmovie.data.local.AppDatabase
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.MovieDetail
import me.abhishekraj.openmovie.data.model.MovieList
import me.abhishekraj.openmovie.data.remote.APIClient
import me.abhishekraj.openmovie.data.remote.MovieDbService
import me.abhishekraj.openmovie.utils.thereIsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor


/**
 * Created by Abhishek Raj on 8/10/2019.
 */

object MoviesRepository {

    private val _movieDetails = MutableLiveData<MovieDetail>()
    var application: Application? = null
    var moviesRepository: MoviesRepository? = null

    private var movieDbService: MovieDbService? = null
    private var movieDao: MovieDao? = null
    private var diskExecutor: Executor? = null
    private var networkExecutor: Executor? = null

    /*
    We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer
    */

    val _moviesList = MutableLiveData<List<Movie>>()

    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails

    val moviesListLiveData: LiveData<List<Movie>>
        get() = _moviesList

    fun getInstance(application: Application): MoviesRepository {
        this.application = application
        if (moviesRepository == null) {
            moviesRepository = MoviesRepository
            movieDao = AppDatabase.getInstance(application).movieDao()
            //create the service
            movieDbService = APIClient.client.create(MovieDbService::class.java)
            diskExecutor = AppExecutors.instance.diskIO
            networkExecutor = AppExecutors.instance.networkIO
        }
        return moviesRepository!!
    }

    fun fetchLiveDataOfMovieList(movieType: String) {
        /*
        https://api.themoviedb.org/3/movie?api_key=user-api-key&page=1
        */
        diskExecutor!!.execute {
            val data = movieDao?.loadAllMoviesListDataByMovieType(movieType)
            Handler(Looper.getMainLooper()).post {
                _moviesList.value = data
            }
        }
        if (thereIsConnection(application!!)) {
            networkExecutor?.execute {
                movieDbService!!.getMovieList(movieType, BuildConfig.MOVIE_DB_API_KEY, 1)
                    .enqueue(object : Callback<MovieList> {
                        override fun onResponse(@NonNull call: Call<MovieList>, @NonNull response: Response<MovieList>) {
                            if (response.body() != null) {
                                _moviesList.value = response.body()!!.results
                                Log.e("my_tag", "repo _moviesList: " + response.body()!!.results.size)
                                diskExecutor?.execute {
                                    movieDao?.deleteAllByType(movieType)
                                    for (movie in response.body()!!.results) {
                                        movie.movieType = movieType
                                        movieDao!!.insertMovie(movie)
                                    }
                                }
                            }
                        }

                        override fun onFailure(@NonNull call: Call<MovieList>, @NonNull throwable: Throwable) {

                        }
                    })
            }
        }

    }


    fun getMovieDetails(movieId: String) {
        diskExecutor?.execute {
            movieDbService?.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY, "videos,reviews,credits")
                ?.enqueue(object : Callback<MovieDetail> {
                    override fun onResponse(@NonNull call: Call<MovieDetail>, @NonNull response: Response<MovieDetail>) {
                        Log.e(TAG, "getMovieDetails onResponse: " + response)
                        if (response.body() != null) {
                            _movieDetails.value = response.body()
                        }
                    }

                    override fun onFailure(@NonNull call: Call<MovieDetail>, @NonNull throwable: Throwable) {

                    }
                })
        }
    }

    private const val TAG = "MoviesRepository"
}