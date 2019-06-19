package me.abhishekraj.openmovie.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.abhishekraj.openmovie.data.local.TypeConverterForMovieArrayList


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

@Parcelize
class MovieList(

    @SerializedName("page")
    @Expose
    var page: Long = 0,
    @SerializedName("total_results")
    @Expose
    var totalResults: Long = 0,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0,
    @Expose
    @TypeConverters(TypeConverterForMovieArrayList::class)
    @SerializedName("results")
    var results: List<Movie> = ArrayList<Movie>()

) : Parcelable

@Parcelize
@Entity(tableName = "movietable")
class Movie(
    @Expose(serialize = false)
    var movieType: String = "popular",
    @SerializedName("vote_count")
    @Expose
    @PrimaryKey
    var voteCount: Long = 0,
    @SerializedName("id")
    @Expose
    var id: Long = 0,
    @SerializedName("video")
    @Expose
    var isVideo: Boolean = false,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.toDouble(),
    @SerializedName("title")
    @Expose
    var title: String? = "",
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.toDouble(),
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = "",
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = "",
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = "",
    @SerializedName("genre_ids")
    @Expose
    @Ignore
    var genreIds: List<Long>? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = "",
    @SerializedName("adult")
    @Expose
    var isAdult: Boolean = false,
    @SerializedName("overview")
    @Expose
    var overview: String? = "",
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = ""
) : Parcelable {

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id.equals(newItem.id)
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.originalTitle?.equals(newItem.originalTitle)!!
        }
    }
}