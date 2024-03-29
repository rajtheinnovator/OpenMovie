@file:JvmName("BindingUtils")

package me.abhishekraj.openmovie.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import de.hdodenhof.circleimageview.CircleImageView
import me.abhishekraj.openmovie.R
import java.lang.String


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

@BindingAdapter("imageSrc")
fun ImageView.loadImage(url: String?) {
    GlideApp.with(context)
        .load("https://image.tmdb.org/t/p/w500" + url)
        .error(R.drawable.image_not_found)
        .placeholder(R.drawable.posterplaceholder)
        .into(this)
}

@BindingAdapter("loadYoutubeThumbnail")
fun loadYoutubeThumbnail(imageView: ImageView, url: String?) {
    GlideApp.with(imageView.context)
        .load("http://img.youtube.com/vi/" + url + "/0.jpg")
        .error(R.drawable.image_not_found)
        .placeholder(R.drawable.posterplaceholder)
        .into(imageView)
}

@BindingAdapter("imageSrcIntoCircularImageView")
fun loadImageIntoCircleImageView(circleImageView: CircleImageView, url: String?) {
    GlideApp.with(circleImageView.context)
        .load("https://image.tmdb.org/t/p/w500" + url)
        .error(R.drawable.image_not_found)
        .placeholder(R.drawable.posterplaceholder)
        .into(circleImageView)
}

@BindingAdapter("setAdapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

@BindingAdapter("rating")
fun setRating(simpleRatingBar: SimpleRatingBar, voteAverage: Double) {
    simpleRatingBar.rating = (voteAverage / 2).toFloat()
}

@BindingAdapter("duration")
fun updateDuration(textView: TextView, runtime: Long) {
    var movieDurationString = ""
    if (runtime < 60) {
        movieDurationString = runtime.toString() + " mins"
    } else if (60 < runtime && runtime < 120) {
        movieDurationString = "1 Hr " + String.valueOf(runtime - 60) + " mins"
    } else if (120 < runtime && runtime < 180) {
        movieDurationString = "2 Hrs " + String.valueOf(runtime - 120) + " mins"
    } else if (180 < runtime && runtime < 240) {
        movieDurationString = "3 Hrs " + String.valueOf(runtime - 180) + " mins"
    } else {
        movieDurationString = String.valueOf(runtime) + " mins"
    }
    textView.setText(movieDurationString)
}


@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
