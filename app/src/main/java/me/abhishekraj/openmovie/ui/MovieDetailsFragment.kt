package me.abhishekraj.openmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.databinding.FragmentDetailsBinding


class MovieDetailsFragment : androidx.fragment.app.Fragment() {

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MovieListViewModel::class.java)
    }
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        //These are for making up button work.
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarMovieDetail)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Get an instance of view model and pass it to the binding implementation
        binding.viewModel = viewModel
        binding.itemMovieDetail.movie
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