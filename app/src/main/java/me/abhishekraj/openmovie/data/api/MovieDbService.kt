package me.abhishekraj.openmovie.data.api

import me.abhishekraj.openmovie.data.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

interface MovieDbService {

    /*
    https://api.themoviedb.org/3/discover/movie?api_key=user-api-key&region=IN
    &language=hi&sort_by=popularity.desc&release_date.gte=2017-08-01&with_release_type=3|2
    */

    /**
     * Retrieve list of articles
     */

    @GET("3/discover/movie/{movie_type}")
    fun getMovieList(
        @Path("movie_type") movieType: String,
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String,
        @Query("with_release_type") releaseType: String,
        @Query("page") pageNumber: Int
    ): Call<MovieList>
}