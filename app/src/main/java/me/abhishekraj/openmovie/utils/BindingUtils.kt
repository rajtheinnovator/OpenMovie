@file:JvmName("BindingUtils")

package me.abhishekraj.openmovie.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import me.abhishekraj.openmovie.R

/**
 * Created by Abhishek Raj on 6/19/2019.
 */
@BindingAdapter("imageSrc")
fun ImageView.loadImage(url: String) {
    GlideApp.with(context)
        .load("https://image.tmdb.org/t/p/w500" + url)
        .error(R.drawable.image_not_found)
        .into(this)
}