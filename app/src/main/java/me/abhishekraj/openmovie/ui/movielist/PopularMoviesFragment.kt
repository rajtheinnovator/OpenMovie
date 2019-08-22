package me.abhishekraj.openmovie.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.PopularMoviesBinding
import me.abhishekraj.openmovie.di.Injectable
import me.abhishekraj.openmovie.util.autoCleared
import javax.inject.Inject

/**
 * Created by Abhishek Raj on 8/21/2019.
 */

class PopularMoviesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieListViewModel by viewModels<MovieListViewModel> { viewModelFactory }

    private var moviesAdapter by autoCleared<MoviesListAdapter>()

    private var movieListBinding by autoCleared<PopularMoviesBinding>()

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_popular_movie, container, false
        )

        //Set adapter, divider and default animator to the recycler view
        moviesAdapter = MoviesListAdapter("popular")

        with(movieListBinding.rvMovieList) {
            itemAnimator = null
            adapter = moviesAdapter

        }

        return movieListBinding.root
    }

    private fun fetchMovies() {
        moviesAdapter.movieList = ArrayList()
        //Claim the list from the view model and observe the results
        movieListViewModel.fetchMoviesResource("popular").observe(
            viewLifecycleOwner,
            Observer { resource ->
                val movies = resource.data?.results
                if (!movies.isNullOrEmpty()) {
                    val movieList = ArrayList<Movie>()
                    movieList.addAll(movies)
                    moviesAdapter.onNewData(movieList)
                }
            }
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //When using livedata inside movieListBinding implementation, we should specify the lifecycle owner
        movieListBinding.lifecycleOwner = this.viewLifecycleOwner
        fetchMovies()
    }

    companion object {
        private const val TAG = "PopularMoviesFragment"
    }
}