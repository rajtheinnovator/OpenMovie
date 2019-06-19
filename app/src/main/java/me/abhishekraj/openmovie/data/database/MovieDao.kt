package me.abhishekraj.openmovie.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import me.abhishekraj.openmovie.data.model.Movie

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movietable ORDER BY timeAdded ASC")
    fun loadAllMoviesLiveData(): LiveData<List<Movie>>

    @Query("SELECT * FROM movietable WHERE movieType = :type ORDER BY timeAdded ASC")
    fun loadAllMoviesLiveDataByMovieType(type: String): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movietable WHERE title = :id")
    fun loadMovieById(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movietable ORDER BY id")
    fun loadAllMovies(): List<Movie>
}