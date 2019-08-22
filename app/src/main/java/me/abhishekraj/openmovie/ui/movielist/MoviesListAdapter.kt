package me.abhishekraj.openmovie.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.ItemBinding

/**
 * Created by Abhishek Raj on 8/9/2019.
 */

class MoviesListAdapter(val movieType: String) :
    RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
        holder.binding.root.setOnClickListener {
            if (movieType.equals("top_rated")) {
                val bundle = Bundle()
                bundle.putParcelable("movie", movieList.get(position))
                bundle.putString("type", "top_rated")
                holder.binding.root.findNavController().navigate(
                    R.id.action_topRatedMoviesFragment_to_movieDetailsFragment,
                    bundle
                )
            } else if (movieType.equals("popular")) {
                val bundle = Bundle()
                bundle.putParcelable("movie", movieList.get(position))
                bundle.putString("type", "popular")
                holder.binding.root.findNavController().navigate(
                    R.id.action_popularMoviesFragment_to_movieDetailsFragment2,
                    bundle
                )
            }
        }
    }

    var movieList = ArrayList<Movie>()
        set(value) {
            field = value
        }

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return movieList.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.from(parent)

    class MovieViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            currentMovie: Movie?
        ) {
            binding.movie = currentMovie ?: Movie()
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val binding = DataBindingUtil.inflate<ItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_poster, parent, false
                )
                return MovieViewHolder(binding)
            }
        }
    }

    fun onNewData(newData: ArrayList<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallback(newData, movieList))
        movieList.clear()
        movieList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        private const val TAG = "MoviesListAdapter"
    }
}