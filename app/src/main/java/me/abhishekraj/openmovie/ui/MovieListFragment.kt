package me.abhishekraj.openmovie.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.databinding.MovieListBinding

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MovieListViewModel::class.java)
    }

    private lateinit var mAdapter: MoviesAdapter
    private lateinit var binding: MovieListBinding


    init {
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.app_bar_main, container, false
        )

        //Set adapter, divider and default animator to the recycler view
        mAdapter = MoviesAdapter()
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(),
            LinearLayoutManager.VERTICAL
        )
        binding.included.bnvMenuOption.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.getItemId()) {
                    R.id.navigation_popular -> {
                        binding.toolbar.setTitle("Popular Movies")
                        return true
                    }
                    R.id.navigation_top_rated -> {
                        binding.toolbar.setTitle("Top Rated Movies")
                        return true
                    }
                    else -> {
                        binding.toolbar.setTitle("Popular Movies")
                    }
                }
                return false
            }
        })
        with(binding.included.rvMovieList) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //When using livedata inside binding implementation, we should specify the lifecycle owner
        binding.lifecycleOwner = this.viewLifecycleOwner

        //Claim the list from the view model and observe the results
        movieListViewModel.movieList?.observe(this, Observer { movies ->
            if (!movies.isNullOrEmpty()) {
                mAdapter.movieList = movies
                Log.d(TAG, "movies are received. list size: " + movies.size)
            }
        })
    }

    companion object {
        private const val TAG = "MovieListFragment"
    }
}
