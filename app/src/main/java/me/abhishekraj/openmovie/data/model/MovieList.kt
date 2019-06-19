package me.abhishekraj.openmovie.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
    @SerializedName("results")
    var results: List<Movie> = ArrayList<Movie>()

) : Parcelable

@Parcelize
class Movie(

    @SerializedName("vote_count")
    @Expose
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
    var title: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.toDouble(),
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Long>? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("adult")
    @Expose
    var isAdult: Boolean = false,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
) : Parcelable