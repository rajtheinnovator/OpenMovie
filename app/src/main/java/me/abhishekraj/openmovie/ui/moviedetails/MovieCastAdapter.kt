package me.abhishekraj.openmovie.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.Cast
import me.abhishekraj.openmovie.databinding.ItemMovieCast

/**
 * Created by Abhishek Raj on 6/25/2019.
 */

class MovieCastAdapter() :
    RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder>() {

    var movieCast: List<Cast>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder =
        MovieCastViewHolder.from(parent)

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) =
        holder.bind(movieCast?.get(position))

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return movieCast?.size ?: 0
    }

    class MovieCastViewHolder(val binding: ItemMovieCast) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentCast: Cast?) {
            binding.cast = currentCast
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieCastViewHolder {
                val binding = DataBindingUtil.inflate<ItemMovieCast>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_cast, parent, false
                )
                return MovieCastViewHolder(binding)
            }
        }
    }
}
