package me.abhishekraj.openmovie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Abhishek Raj on 6/22/2019.
 */

class Genre(
    @SerializedName("id")
    @Expose
    var id: Long = 0,
    @SerializedName("name")
    @Expose
    var name: String? = null
)

class MovieDetail(
    @SerializedName("adult")
    @Expose
    var isAdult: Boolean = false,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any? = null,
    @SerializedName("budget")
    @Expose
    var budget: Long = 0,
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null,
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,
    @SerializedName("id")
    @Expose
    var id: Long = 0,
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.toDouble(),
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,
    @SerializedName("revenue")
    @Expose
    var revenue: Long = 0,
    @SerializedName("runtime")
    @Expose
    var runtime: Long = 0,
    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("video")
    @Expose
    var isVideo: Boolean = false,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.toDouble(),
    @SerializedName("vote_count")
    @Expose
    var voteCount: Long = 0,
    @SerializedName("videos")
    @Expose
    var videos: Videos? = null,
    @SerializedName("reviews")
    @Expose
    var reviews: Reviews? = null
)

class ProductionCompany(
    @SerializedName("id")
    @Expose
    var id: Long = 0,
    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
)

class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)

class VideosResult(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,
    @SerializedName("key")
    @Expose
    var key: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("site")
    @Expose
    var site: String? = null,
    @SerializedName("size")
    @Expose
    var size: Long = 0,
    @SerializedName("type")
    @Expose
    var type: String? = null
)

class ReviewResult(
    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("content")
    @Expose
    var content: String? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
)

class Reviews(
    @SerializedName("page")
    @Expose
    var page: Long = 0,
    @SerializedName("results")
    @Expose
    var reviewResult: List<ReviewResult>? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0,
    @SerializedName("total_results")
    @Expose
    var totalResults: Long = 0
)

class SpokenLanguage(
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)

class Videos(

    @SerializedName("results")
    @Expose
    var videosResult: List<VideosResult>? = null
)