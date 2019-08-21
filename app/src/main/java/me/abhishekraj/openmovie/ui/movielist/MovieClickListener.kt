package me.abhishekraj.openmovie.ui.movielist

import me.abhishekraj.openmovie.data.model.Movie

/**
 * Created by Abhishek Raj on 8/21/2019.
 */

interface MovieClickListener {
    fun onMovieClicked(chosenMovie: Movie)
}