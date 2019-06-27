package me.abhishekraj.openmovie.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.data.model.VideosResult
import me.abhishekraj.openmovie.databinding.FragmentDetailsBinding
import me.abhishekraj.openmovie.utils.UIState

class MovieDetailsFragment : Fragment(), MovieTrailerAdapter.TrailerClickListener {

    override fun onTrailerClicked(clickedTrailer: VideosResult, movieTrailer: ArrayList<VideosResult>?) {
        fragmentManager?.transaction {
            val movieDetailsFragment = MovieTrailerPlayerFragment()
            val bundle = Bundle()
            bundle.putParcelable("selectedVideo", clickedTrailer)
            bundle.putParcelableArrayList("videos", movieTrailer)
            movieDetailsFragment.arguments = bundle
            replace(R.id.fl_fragment_container, movieDetailsFragment)
            addToBackStack("MovieListFragment")
        }
    }

    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    private var movie: Movie? = null

    private val movieDetailsViewModel: MovieDetailsViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MovieDetailsViewModel::class.java)
    }

    private lateinit var movieReviewsAdapter: MovieReviewsAdapter
    private lateinit var movieCastAdapter: MovieCastAdapter
    private lateinit var movieTrailerAdapter: MovieTrailerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable("movie")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        //These are for making up button work.
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentDetailsBinding.toolbarMovieDetail)
        setHasOptionsMenu(true)
        movieReviewsAdapter = MovieReviewsAdapter()
        with(fragmentDetailsBinding.rvMovieReviews) {
            itemAnimator = null
            adapter = movieReviewsAdapter

        }

        movieCastAdapter = MovieCastAdapter()
        with(fragmentDetailsBinding.rvMovieCast) {
            itemAnimator = null
            adapter = movieCastAdapter
        }

        movieTrailerAdapter = MovieTrailerAdapter(this)
        with(fragmentDetailsBinding.rvMovieTrailers) {
            itemAnimator = null
            adapter = movieTrailerAdapter
        }
        return fragmentDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Get an instance of view model and pass it to the fragmentDetailsBinding implementation
        fragmentDetailsBinding.viewModel = movieDetailsViewModel

        //Get the view model instance and pass it to the binding implementation
        fragmentDetailsBinding.uiState = movieDetailsViewModel.uiState
        fetchMovieDetails(movie?.id.toString())
    }

    private fun fetchMovieDetails(movieId: String) {
        movieDetailsViewModel.fetchMovieDetails(movieId)?.observe(this, Observer { movieDetails ->
            if (movieDetails != null) {
                fragmentDetailsBinding.movieDetail = movieDetails
                fragmentDetailsBinding.movie = movie
                fragmentDetailsBinding.itemMovieDetail.movieDetail = movieDetails
                fragmentDetailsBinding.itemMovieDetail.movie = movie
                fragmentDetailsBinding.viewModel = movieDetailsViewModel
                movieReviewsAdapter.reviewResult = movieDetails.reviews?.reviewResult
                movieCastAdapter.movieCast = movieDetails.credits?.cast
                movieTrailerAdapter.movieTrailer = movieDetails.videos?.videosResult
                movieDetailsViewModel.setUiState(UIState.SUCCESS)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //This is for making up button in the toolbar behave like back button
        if (item.itemId == android.R.id.home) {
            fragmentManager?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
    }
}