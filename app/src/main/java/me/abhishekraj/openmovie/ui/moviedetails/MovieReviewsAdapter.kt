package me.abhishekraj.openmovie.ui.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.abhishekraj.openmovie.R
import me.abhishekraj.openmovie.data.model.ReviewResult
import me.abhishekraj.openmovie.databinding.ItemMovieReview

class MovieReviewsAdapter() :
    RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewViewHolder>() {

    var reviewResult: List<ReviewResult>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder =
        MovieReviewViewHolder.from(parent)

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) =
        holder.bind(reviewResult?.get(position))

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return reviewResult?.size ?: 0
    }

    class MovieReviewViewHolder(val binding: ItemMovieReview) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentReview: ReviewResult?) {
            binding.review = currentReview
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieReviewViewHolder {
                val binding = DataBindingUtil.inflate<ItemMovieReview>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_review, parent, false
                )
                return MovieReviewViewHolder(binding)
            }
        }
    }
}
