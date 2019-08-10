package me.abhishekraj.openmovie.ui.movielist

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import me.abhishekraj.openmovie.data.model.Movie
import java.util.*


class MyDiffUtilCallback internal constructor(
    internal var newList: ArrayList<Movie>?,
    internal var oldList: ArrayList<Movie>?
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return if (oldList != null) oldList!!.size else 0
    }

    override fun getNewListSize(): Int {
        return if (newList != null) newList!!.size else 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = newList!![newItemPosition].compareTo(oldList!![oldItemPosition])
        return result == 0
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diff = Bundle()
        if (newItemPosition < newList!!.size && oldItemPosition < oldList!!.size) {
            val newMovie = newList!![newItemPosition]
            val oldMovie = oldList!![oldItemPosition]
            if (!newMovie.originalTitle.equals(oldMovie.originalTitle)) {
                diff.putString("originalTitle", newMovie.originalTitle)
            }
            if (diff.size() == 0) {
                return null
            }
        }
        return diff
    }
}
