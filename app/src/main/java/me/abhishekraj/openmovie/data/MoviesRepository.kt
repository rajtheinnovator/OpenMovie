package me.abhishekraj.openmovie.data

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.abhishekraj.openmovie.BuildConfig
import me.abhishekraj.openmovie.data.local.MovieDao
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.MovieDetail
import me.abhishekraj.openmovie.data.model.MovieList
import me.abhishekraj.openmovie.data.remote.CoroutinesPlants
import me.abhishekraj.openmovie.data.remote.MovieDbService
import me.abhishekraj.openmovie.utils.thereIsConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Abhishek Raj on 8/10/2019.
 */

@Singleton
class MoviesRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val movieDao: MovieDao,
    private val movieDbService: MovieDbService,
    private val application: Application
) {

    private val _movieDetails = MutableLiveData<MovieDetail>()

    /*
    We need a mutable live data here, so that we can modify it, but we
    prefer to pass an immutable live data to the UI layer
    */

    val _listOfMovie = MutableLiveData<List<Movie>>()

    val _movieListMutableLiveData = MutableLiveData<MovieList>()
    val movieListLiveData: LiveData<MovieList>
        get() = _movieListMutableLiveData

    val movieDetails: LiveData<MovieDetail>
        get() = _movieDetails

    val liveDataOfListOfMovie: LiveData<List<Movie>>
        get() = _listOfMovie

    fun fetchLiveDataOfMovieList(movieType: String) {
        /*
        https://api.themoviedb.org/3/movie?api_key=user-api-key&page=1
        */

        appExecutors.diskIO().execute {
            val data = movieDao.loadAllMoviesListDataByMovieType(movieType)
            Handler(Looper.getMainLooper()).post {
                _listOfMovie.value = data
            }
        }
        if (thereIsConnection(application)) {
            appExecutors.networkIO().execute {
                movieDbService.getMovieList(movieType, BuildConfig.MOVIE_DB_API_KEY, 1)
                    .enqueue(object : Callback<MovieList> {
                        override fun onResponse(@NonNull call: Call<MovieList>, @NonNull response: Response<MovieList>) {
                            if (response.body() != null) {
                                _listOfMovie.value = response.body()!!.results
                                Log.e("my_tag", "repo _listOfMovie: " + response.body()!!.results.size)
                                appExecutors.diskIO().execute {
                                    movieDao.deleteAllByType(movieType)
                                    for (movie in response.body()!!.results) {
                                        movie.movieType = movieType
                                        movieDao.insertMovie(movie)
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


    fun loadListOfMovies(movieType: String): LiveData<Resource<MovieList>> {
        return object : NetworkBoundResource<MovieList, MovieList>(
            appExecutors
        ) {
            override fun saveCallResult(item: MovieList) {
                for (movie in item.results) {
                    movie.movieType = movieType
                    movieDao.insertMovie(movie)
                }
            }

            override fun shouldFetch(data: MovieList?): Boolean {
                return thereIsConnection(application)
            }

            override fun loadFromDb() = getMovieListLiveData(movieType)

            override fun createCall() =
                movieDbService.getMovieListResponse(movieType, BuildConfig.MOVIE_DB_API_KEY, 1)

            override fun onFetchFailed() {
                getMovieListLiveData(movieType)
            }
        }.asLiveData()
    }


    private fun getMovieListLiveData(movieType: String): LiveData<MovieList> {
        appExecutors.diskIO().execute {
            val movieList = MovieList()
            val movieListFromDatabase = movieDao.loadAllMoviesListDataByMovieType(movieType)
            if (movieListFromDatabase != null) {
                movieList.results = movieListFromDatabase
            }
            Handler(Looper.getMainLooper()).post {
                _movieListMutableLiveData.value = movieList
            }
        }
        return movieListLiveData
    }

    fun getMovieDetails(movieId: String) {
        appExecutors.diskIO().execute {
            movieDbService.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY, "videos,reviews,credits")
                .enqueue(object : Callback<MovieDetail> {
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

    companion object {
        private const val TAG = "MoviesRepository"
    }

    fun getPlantDetails() {
        appExecutors.diskIO().execute {
            movieDbService.getAllPlants()
                .enqueue(object : Callback<List<CoroutinesPlants>> {
                    override fun onResponse(@NonNull call: Call<List<CoroutinesPlants>>, @NonNull response: Response<List<CoroutinesPlants>>) {
                        Log.e(TAG, "getMovieDetails onResponse: " + response)
                    }

                    override fun onFailure(@NonNull call: Call<List<CoroutinesPlants>>, @NonNull throwable: Throwable) {

                    }
                })
        }
    }
}