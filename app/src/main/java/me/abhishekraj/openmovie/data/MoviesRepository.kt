package me.abhishekraj.openmovie.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.abhishekraj.openmovie.data.local.AppDatabase
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.remote.APIClient
import me.abhishekraj.openmovie.data.remote.MovieBoundaryCallback
import me.abhishekraj.openmovie.data.remote.MovieDbService
import me.abhishekraj.openmovie.utils.thereIsConnection
import java.util.concurrent.Executor


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class MoviesRepository(val application: Application) {

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

    private var _movies = MutableLiveData<PagedList<Movie>?>()

    val movies: LiveData<PagedList<Movie>?>
        get() = _movies

    fun getListOfMovies(movieType: String) {

    }

    fun getLiveDataOfPagedList(movieType: String): LiveData<PagedList<Movie>?>? {

        /*
     https://api.themoviedb.org/3/movie?api_key=user-api-key&page=1
     */

        val movieBoundaryCallback = MovieBoundaryCallback(
            movieDbService!!,
            movieType, "popularity.desc", "3|2", executor!!, movieDao!!, 1
        )

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .setPrefetchDistance(3)
            .build()

        if (thereIsConnection(application)) {
            executor?.execute {
                movieDao?.deleteAll()
            }
        }
        return LivePagedListBuilder(movieDao!!.getAllMoviePageByMovieType(movieType), pagedListConfig)
            .setFetchExecutor(executor!!)
            .setBoundaryCallback(movieBoundaryCallback)
            .build()
    }

    companion object {
        private const val TAG = "MoviesRepository"
    }

}