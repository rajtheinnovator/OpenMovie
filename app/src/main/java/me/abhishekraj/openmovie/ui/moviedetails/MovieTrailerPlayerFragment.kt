package me.abhishekraj.openmovie.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.VideosResult
import me.abhishekraj.openmovie.databinding.FragmentPlayMovieTrailer

/**
 * Created by Abhishek Raj on 6/26/2019.
 */

class MovieTrailerPlayerFragment : Fragment(), MovieTrailerPlayerAdapter.TrailerClicked {

    override fun onTrailerCued(name: String) {
        Log.d("my___tags", "name: " + name)
        (activity as AppCompatActivity).supportActionBar!!.title = name
    }

    private lateinit var fragmentPlayMovieTrailerBinding: FragmentPlayMovieTrailer
    private var clickedTrailer: VideosResult? = null
    private var movieTrailers: ArrayList<VideosResult>? = null
    private var trailerClicked: MovieTrailerPlayerAdapter.TrailerClicked? = null

    private lateinit var movieTrailerPlayerAdapter: MovieTrailerPlayerAdapter

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = clickedTrailer?.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trailerClicked = this
        clickedTrailer = arguments?.getParcelable("selectedVideo")
        movieTrailers = arguments?.getParcelableArrayList("videos")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentPlayMovieTrailerBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_movie_trailer_player,
                container,
                false
            )

        setHasOptionsMenu(true)
        movieTrailerPlayerAdapter =
            MovieTrailerPlayerAdapter(movieTrailers, this.lifecycle, trailerClicked)
        with(fragmentPlayMovieTrailerBinding.rvPlayMovieTrailer) {
            itemAnimator = null
            adapter = movieTrailerPlayerAdapter

        }

        return fragmentPlayMovieTrailerBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieTrailerPlayerAdapter.videoIds = movieTrailers

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //This is for making up button in the toolbar behave like back button
        if (item.itemId == android.R.id.home) {
            fragmentManager?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG = "MovieTrailerPlayerFragment"
    }
}