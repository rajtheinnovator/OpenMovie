package me.abhishekraj.openmovie.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.ItemBinding

/**
 * Created by Abhishek Raj on 8/9/2019.
 */

class MoviesListAdapter(
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position), movieClickListener)
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
            currentMovie: Movie?,
            movieClickListener: MovieClickListener
        ) {
            binding.movieItemClick = movieClickListener
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