package me.abhishekraj.openmovie.data.remote

import androidx.lifecycle.LiveData
import me.abhishekraj.openmovie.data.ApiResponse
import me.abhishekraj.openmovie.data.model.MovieDetail
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

    @GET("3/movie/{movie_type}")
    fun getMovieList(
        @Path("movie_type") movieType: String,
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): Call<MovieList>

    @GET("3/movie/{movie_type}")
    fun getMovieListResponse(
        @Path("movie_type") movieType: String,
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): LiveData<ApiResponse<MovieList>>

    @GET("3/discover/movie/{movie_type}")
    fun discoverMoviesByFilters(
        @Path("movie_type") movieType: String,
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String,
        @Query("with_release_type") releaseType: String,
        @Query("page") pageNumber: Int
    ): Call<MovieList>

    /**
     * Retrieve details of a movie
     */

    /*
     @link" "https://api.themoviedb.org/3/movie/284052?api_key=user-api-key&append_to_response=videos,reviews"
    * */

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") retrieveExtraDetails: String
    ): Call<MovieDetail>
}