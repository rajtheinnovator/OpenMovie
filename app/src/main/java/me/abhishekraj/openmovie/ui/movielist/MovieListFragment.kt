package me.abhishekraj.openmovie.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.MovieListBinding
import me.abhishekraj.openmovie.ui.moviedetails.MovieDetailsFragment

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class MovieListFragment : Fragment(), MoviesPagedListAdapter.MovieClickListener {

    override fun onMovieClicked(chosenMovie: Movie) {
        movieListViewModel.chosenMovie = chosenMovie
        fragmentManager?.transaction {
            val movieDetailsFragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable("movie", chosenMovie)
            movieDetailsFragment.arguments = bundle
            replace(R.id.fl_fragment_container, movieDetailsFragment)
            addToBackStack("MovieListFragment")
        }
    }

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MovieListViewModel::class.java)
    }

    private lateinit var moviesAdapter: MoviesPagedListAdapter
    private lateinit var movieListBinding: MovieListBinding


    init {
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        movieListBinding = DataBindingUtil.inflate(
            inflater, R.layout.app_bar_main, container, false
        )

        //Set adapter, divider and default animator to the recycler view
        moviesAdapter = MoviesPagedListAdapter(this)

        //set default movie type to be popular
        movieListBinding.toolbar.setTitle("Popular Movies")

        movieListBinding.included.bnvMenuOption.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.navigation_popular -> {
                        movieListBinding.toolbar.setTitle("Popular Movies")
                        fetchMovies("popular")
                        return true
                    }
                    R.id.navigation_top_rated -> {
                        movieListBinding.toolbar.setTitle("Top Rated Movies")
                        fetchMovies("top_rated")
                        return true
                    }
                    else -> {
                        movieListBinding.toolbar.setTitle("Popular Movies")
                    }
                }
                return false
            }
        })

        with(movieListBinding.included.rvMovieList) {
            itemAnimator = null
            adapter = moviesAdapter

        }

        return movieListBinding.root
    }

    private fun fetchMovies(movieType: String) {
        moviesAdapter.movieList = ArrayList()
        //Claim the list from the view model and observe the results
        movieListViewModel.fetchMovies(movieType)?.observe(this, Observer { movies ->
            if (!movies.isNullOrEmpty()) {
                val movieList = ArrayList<Movie>()
                movieList.addAll(movies)
                moviesAdapter.movieList = movieList
                moviesAdapter.submitList(movies)
                Log.d(TAG, "movies are received. list size: " + movies.size)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //When using livedata inside movieListBinding implementation, we should specify the lifecycle owner
        movieListBinding.lifecycleOwner = this.viewLifecycleOwner
        fetchMovies("popular")
    }

    companion object {
        private const val TAG = "MovieListFragment"
    }
}
