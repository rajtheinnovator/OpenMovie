package me.abhishekraj.openmovie.data.model

import android.util.Log
import androidx.annotation.NonNull
import androidx.paging.PagedList
import me.abhishekraj.openmovie.BuildConfig
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.remote.MovieDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * Created by Abhishek Raj on 6/20/2019.
 */

class MovieBoundaryCallback(
    private val movieDbService: MovieDbService,
    private val movieType: String,
    private val sortBy: String,
    private val releaseType: String,
    private val mExecutor: Executor, private val movieDao: MovieDao
) : PagedList.BoundaryCallback<Movie>() {

    private var pageNumber: Int = 0

    init {
        pageNumber = 1
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        movieDbService.getMovieList(movieType, BuildConfig.MOVIE_DB_API_KEY, pageNumber)
            .enqueue(object : Callback<MovieList> {
                override fun onResponse(@NonNull call: Call<MovieList>, @NonNull response: Response<MovieList>) {
                    Log.d("my_tag", "onZeroItemsLoaded response: " + response)
                    if (response.body() != null) {
                        mExecutor.execute(Runnable {
                            for (movie in response.body()!!.results) {
                                movieDao.insertMovie(movie)
                                Log.d("my_tag", "onZeroItemsLoaded movie: " + movie.originalTitle)
                            }
                        })
                    }
                }

                override fun onFailure(@NonNull call: Call<MovieList>, @NonNull throwable: Throwable) {

                }
            })

        pageNumber += 1
    }

    override fun onItemAtFrontLoaded(@NonNull itemAtFront: Movie) {
        super.onItemAtFrontLoaded(itemAtFront)
    }

    override fun onItemAtEndLoaded(@NonNull itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
        movieDbService.getMovieList(movieType, BuildConfig.MOVIE_DB_API_KEY, pageNumber)
            .enqueue(object : Callback<MovieList> {
                override fun onResponse(@NonNull call: Call<MovieList>, @NonNull response: Response<MovieList>) {
                    Log.d("my_tag", "onItemAtEndLoaded onResponse called")
                    if (response.body() != null) {
                        mExecutor.execute(Runnable {
                            for (movie in response.body()!!.results) {
                                Log.d("my_tag", "onItemAtEndLoaded movie: " + movie.originalTitle)
                                movieDao.insertMovie(movie)
                            }
                        })
                    }
                }

                override fun onFailure(@NonNull call: Call<MovieList>, @NonNull throwable: Throwable) {

                }
            })
        pageNumber += 1
    }
}