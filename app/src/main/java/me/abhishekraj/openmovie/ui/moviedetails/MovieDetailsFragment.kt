package me.abhishekraj.openmovie.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.FragmentDetailsBinding
import me.abhishekraj.openmovie.di.Injectable
import me.abhishekraj.openmovie.util.autoCleared
import me.abhishekraj.openmovie.utils.UIState
import javax.inject.Inject


class MovieDetailsFragment : Fragment(), Injectable {

    private var movieReviewsAdapter by autoCleared<MovieReviewsAdapter>()

    private var fragmentDetailsBinding by autoCleared<FragmentDetailsBinding>()

    private var movie: Movie? = null
    private lateinit var movieType: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieDetailsViewModel by viewModels<MovieDetailsViewModel> { viewModelFactory }

    private var movieCastAdapter by autoCleared<MovieCastAdapter>()
    private var movieTrailerAdapter by autoCleared<MovieTrailerAdapter>()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = movie?.originalTitle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable("movie")
        movieType = arguments?.getString("type") ?: "popular"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar!!.setTitle(movie?.originalTitle)

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

        movieTrailerAdapter = MovieTrailerAdapter(movieType)
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

        fetchMovieDetails(movie?.id.toString())
    }

    private fun fetchMovieDetails(movieId: String) {
        movieDetailsViewModel.fetchMovieDetails(movieId)
            ?.observe(viewLifecycleOwner, Observer { movieDetails ->
                if (movieDetails != null) {
                    movieDetailsViewModel.setUiState(UIState.SUCCESS)
                    fragmentDetailsBinding.movieDetail = movieDetails
                    fragmentDetailsBinding.movie = movie
                    fragmentDetailsBinding.itemMovieDetail.movieDetail = movieDetails
                    fragmentDetailsBinding.itemMovieDetail.movie = movie
                    fragmentDetailsBinding.viewModel = movieDetailsViewModel
                    movieReviewsAdapter.reviewResult = movieDetails.reviews?.reviewResult
                    movieCastAdapter.movieCast = movieDetails.credits?.cast
                    movieTrailerAdapter.movieTrailer = movieDetails.videos?.videosResult
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