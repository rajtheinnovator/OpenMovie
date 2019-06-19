package me.abhishekraj.openmovie.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.abhishekraj.openmovie.data.local.AppDatabase
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.MovieBoundaryCallback
import me.abhishekraj.openmovie.data.remote.APIClient
import me.abhishekraj.openmovie.data.remote.MovieDbService
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

    fun getListOfMovies(movieType: MutableLiveData<String>?) {

    }

    fun getLiveDataOfPagedList(movieType: String): LiveData<PagedList<Movie>?>? {
        /*
        https://api.themoviedb.org/3/discover/movie?api_key=user-api-key&region=IN
        &language=hi&sort_by=popularity.desc&release_date.gte=2017-08-01&with_release_type=3|2
        */

        val movieBoundaryCallback = MovieBoundaryCallback(
            movieDbService!!,
            movieType, "popularity.desc", "3|2", executor!!, movieDao!!
        )

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20 * 3)
            .setPageSize(20)
            .setPrefetchDistance(10)
            .build()
        return LivePagedListBuilder(movieDao!!.getAllMoviePageByMovieType(movieType), pagedListConfig)
            .setFetchExecutor(executor!!)
            .setBoundaryCallback(movieBoundaryCallback)
            .build()
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