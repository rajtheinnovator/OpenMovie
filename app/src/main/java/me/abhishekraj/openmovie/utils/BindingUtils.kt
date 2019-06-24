@file:JvmName("BindingUtils")

package me.abhishekraj.openmovie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import me.abhishekraj.openmovie.R

/**
 * Created by Abhishek Raj on 6/19/2019.
 */

@BindingAdapter("imageSrc")
fun ImageView.loadImage(url: String) {
    GlideApp.with(context)
        .load("https://image.tmdb.org/t/p/w500" + url)
        .error(R.drawable.image_not_found)
        .placeholder(R.drawable.posterplaceholder)
        .into(this)
}

@BindingAdapter("setAdapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

@BindingAdapter("rating")
fun SimpleRatingBar.setRating(voteAverage: Double) {
    this.setRating(voteAverage / 2)
}