package me.abhishekraj.openmovie.data

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.abhishekraj.openmovie.BuildConfig
import me.abhishekraj.openmovie.data.local.AppDatabase
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.MovieDetail
import me.abhishekraj.openmovie.data.remote.APIClient
import me.abhishekraj.openmovie.data.remote.MovieBoundaryCallback
import me.abhishekraj.openmovie.data.remote.MovieDbService
import me.abhishekraj.openmovie.utils.thereIsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class MoviesRepository(val application: Application) {

    val _movieDetails = MutableLiveData<MovieDetail>()

    private var movieDbService: MovieDbService? = null
    private var movieDao: MovieDao? = null
    private var executor: Executor? = null

    init {
        movieDao = AppDatabase.getInstance(application).movieDao()
        //create the service
        movieDbService = APIClient.client.create<MovieDbService>(MovieDbService::class.java)
        executor = AppExecutors.instance.diskIO
    }

    /*
    We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer
    */

    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails

    fun getLiveDataOfPagedList(movieType: String): LiveData<PagedList<Movie>?>? {

        /*
     https://api.themoviedb.org/3/movie?api_key=user-api-key&page=1
     */

        if (thereIsConnection(application)) {
            executor?.execute {
                movieDao?.deleteAllByType(movieType)
            }
        }

        val movieBoundaryCallback = MovieBoundaryCallback(
            movieDbService!!,
            movieType, "popularity.desc", "3|2", executor!!, movieDao!!, 1
        )

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(0)
            .build()

        return LivePagedListBuilder(movieDao!!.getAllMoviePageByMovieType(movieType), pagedListConfig)
            .setFetchExecutor(executor!!)
            .setBoundaryCallback(movieBoundaryCallback)
            .build()
    }

    fun getMovieDetails(movieId: String): LiveData<MovieDetail> {
        executor?.execute {
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

        return movieDetails
    }

    companion object {
        private const val TAG = "MoviesRepository"
    }

}