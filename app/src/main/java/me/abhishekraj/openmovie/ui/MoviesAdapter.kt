package me.abhishekraj.openmovie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Movie
import me.abhishekraj.openmovie.databinding.ItemBinding

class MoviesAdapter() :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movieList: List<Movie>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.from(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movieList?.get(position))

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return movieList?.size ?: 0
    }

    class MovieViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMovie: Movie?) {
            binding.movie = currentMovie
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
}
