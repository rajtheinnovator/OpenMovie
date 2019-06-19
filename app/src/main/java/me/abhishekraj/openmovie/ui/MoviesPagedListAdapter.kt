package me.abhishekraj.openmovie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.ItemBinding


/**
 * Created by Abhishek Raj on 6/20/2019.
 */

class MoviesPagedListAdapter(
) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(Movie.DIFF_CALLBACK) {

    var movieList: List<Movie>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MovieViewHolder).bind(movieList?.get(position))

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return movieList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.from(parent)

    class MovieViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentMovie: Movie?) {
            binding.movie = currentMovie
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val binding = DataBindingUtil.inflate<ItemBinding>(
                    LayoutInflater.from(parent.context),
                    me.abhishekraj.openmovie.R.layout.item_movie_poster, parent, false
                )
                return MovieViewHolder(binding)
            }
        }
    }
}