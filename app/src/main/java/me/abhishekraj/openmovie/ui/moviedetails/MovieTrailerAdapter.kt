package me.abhishekraj.openmovie.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.VideosResult
import me.abhishekraj.openmovie.databinding.ItemMovieTrailer
import java.util.*

/**
 * Created by Abhishek Raj on 6/26/2019.
 */

class MovieTrailerAdapter(val movieType: String) :
    RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder>() {

    var movieTrailer: List<VideosResult>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrailerViewHolder =
        MovieTrailerViewHolder.from(parent)

    override fun onBindViewHolder(holder: MovieTrailerViewHolder, position: Int) {
        holder.bind(movieTrailer?.get(position), movieTrailer)
        holder.binding.root.setOnClickListener {
            if (movieType.equals("top_rated")) {
                val bundle = Bundle()
                bundle.putParcelable("selectedVideo", movieTrailer?.get(position))
                bundle.putParcelableArrayList("videos", movieTrailer as ArrayList<VideosResult>?)
                holder.binding.root.findNavController().navigate(
                    R.id.action_movieDetailsFragment_to_movieTrailerPlayerFragment,
                    bundle
                )
            } else {
                val bundle = Bundle()
                bundle.putParcelable("selectedVideo", movieTrailer?.get(position))
                bundle.putParcelableArrayList("videos", movieTrailer as ArrayList<VideosResult>?)
                holder.binding.root.findNavController().navigate(
                    R.id.action_movieDetailsFragment2_to_movieTrailerPlayerFragment2,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return movieTrailer?.size ?: 0
    }

    class MovieTrailerViewHolder(val binding: ItemMovieTrailer) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            currentVideo: VideosResult?,
            movieTrailerList: List<VideosResult>?
        ) {
            binding.video = currentVideo
            binding.videoList = movieTrailerList as ArrayList<VideosResult>?
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieTrailerViewHolder {
                val binding = DataBindingUtil.inflate<ItemMovieTrailer>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_trailer, parent, false
                )
                return MovieTrailerViewHolder(binding)
            }
        }
    }

}