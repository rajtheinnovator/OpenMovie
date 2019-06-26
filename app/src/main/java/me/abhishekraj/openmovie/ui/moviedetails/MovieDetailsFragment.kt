package me.abhishekraj.openmovie.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.FragmentDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        //These are for making up button work.
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarMovieDetail)
        setHasOptionsMenu(true)
        movieReviewsAdapter = MovieReviewsAdapter()
        with(binding.rvMovieReviews) {
            itemAnimator = null
            adapter = movieReviewsAdapter

        }

        movieCastAdapter = MovieCastAdapter()
        with(binding.rvMovieCast) {
            itemAnimator = null
            adapter = movieCastAdapter
        }

        movieTrailerAdapter = MovieTrailerAdapter()
        with(binding.rvMovieTrailers) {
            itemAnimator = null
            adapter = movieTrailerAdapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Get an instance of view model and pass it to the binding implementation
        binding.viewModel = movieDetailsViewModel
        fetchMovieDetails(movie?.id.toString())
    }

    private fun fetchMovieDetails(movieId: String) {
        movieDetailsViewModel.fetchMovieDetails(movieId)?.observe(this, Observer { movies ->
            if (movies != null) {
                binding.itemMovieDetail.movie = movie
                movieReviewsAdapter.reviewResult = movies.reviews?.reviewResult
                movieCastAdapter.movieCast = movies.credits?.cast
                movieTrailerAdapter.movieTrailer = movies.videos?.videosResult
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