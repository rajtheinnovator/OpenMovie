package me.abhishekraj.openmovie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Abhishek Raj on 6/22/2019.
 */

class Genre {

    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("name")
    @Expose
    var name: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param name
     */
    constructor(id: Long, name: String) : super() {
        this.id = id
        this.name = name
    }

}

class MovieDetail {

    @SerializedName("adult")
    @Expose
    var isAdult: Boolean = false
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any? = null
    @SerializedName("budget")
    @Expose
    var budget: Long = 0
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double = 0.toDouble()
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @SerializedName("revenue")
    @Expose
    var revenue: Long = 0
    @SerializedName("runtime")
    @Expose
    var runtime: Long = 0
    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var isVideo: Boolean = false
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double = 0.toDouble()
    @SerializedName("vote_count")
    @Expose
    var voteCount: Long = 0
    @SerializedName("videos")
    @Expose
    var videos: Videos? = null
    @SerializedName("reviews")
    @Expose
    var reviews: Reviews? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param budget
     * @param genres
     * @param spokenLanguages
     * @param runtime
     * @param backdropPath
     * @param voteCount
     * @param id
     * @param title
     * @param releaseDate
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param video
     * @param popularity
     * @param revenue
     * @param productionCountries
     * @param reviews
     * @param status
     * @param videos
     * @param originalLanguage
     * @param adult
     * @param imdbId
     * @param homepage
     * @param overview
     * @param belongsToCollection
     * @param productionCompanies
     * @param tagline
     */
    constructor(
        adult: Boolean,
        backdropPath: String,
        belongsToCollection: Any,
        budget: Long,
        genres: List<Genre>,
        homepage: String,
        id: Long,
        imdbId: String,
        originalLanguage: String,
        originalTitle: String,
        overview: String,
        popularity: Double,
        posterPath: String,
        productionCompanies: List<ProductionCompany>,
        productionCountries: List<ProductionCountry>,
        releaseDate: String,
        revenue: Long,
        runtime: Long,
        spokenLanguages: List<SpokenLanguage>,
        status: String,
        tagline: String,
        title: String,
        video: Boolean,
        voteAverage: Double,
        voteCount: Long,
        videos: Videos,
        reviews: Reviews
    ) : super() {
        this.isAdult = adult
        this.backdropPath = backdropPath
        this.belongsToCollection = belongsToCollection
        this.budget = budget
        this.genres = genres
        this.homepage = homepage
        this.id = id
        this.imdbId = imdbId
        this.originalLanguage = originalLanguage
        this.originalTitle = originalTitle
        this.overview = overview
        this.popularity = popularity
        this.posterPath = posterPath
        this.productionCompanies = productionCompanies
        this.productionCountries = productionCountries
        this.releaseDate = releaseDate
        this.revenue = revenue
        this.runtime = runtime
        this.spokenLanguages = spokenLanguages
        this.status = status
        this.tagline = tagline
        this.title = title
        this.isVideo = video
        this.voteAverage = voteAverage
        this.voteCount = voteCount
        this.videos = videos
        this.reviews = reviews
    }

}

class ProductionCompany {

    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param originCountry
     * @param name
     * @param logoPath
     */
    constructor(id: Long, logoPath: String, name: String, originCountry: String) : super() {
        this.id = id
        this.logoPath = logoPath
        this.name = name
        this.originCountry = originCountry
    }

}

class ProductionCountry {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param iso31661
     * @param name
     */
    constructor(iso31661: String, name: String) : super() {
        this.iso31661 = iso31661
        this.name = name
    }

}

class Result {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("site")
    @Expose
    var site: String? = null
    @SerializedName("size")
    @Expose
    var size: Long = 0
    @SerializedName("type")
    @Expose
    var type: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param site
     * @param iso6391
     * @param id
     * @param iso31661
     * @param name
     * @param type
     * @param key
     * @param size
     */
    constructor(
        id: String,
        iso6391: String,
        iso31661: String,
        key: String,
        name: String,
        site: String,
        size: Long,
        type: String
    ) : super() {
        this.id = id
        this.iso6391 = iso6391
        this.iso31661 = iso31661
        this.key = key
        this.name = name
        this.site = site
        this.size = size
        this.type = type
    }

}

class Result_ {

    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param content
     * @param author
     * @param url
     */
    constructor(author: String, content: String, id: String, url: String) : super() {
        this.author = author
        this.content = content
        this.id = id
        this.url = url
    }

}

class Reviews {

    @SerializedName("page")
    @Expose
    var page: Long = 0
    @SerializedName("results")
    @Expose
    var results: List<Result_>? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Long = 0
    @SerializedName("total_results")
    @Expose
    var totalResults: Long = 0

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    constructor(page: Long, results: List<Result_>, totalPages: Long, totalResults: Long) : super() {
        this.page = page
        this.results = results
        this.totalPages = totalPages
        this.totalResults = totalResults
    }

}

class SpokenLanguage {

    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param iso6391
     * @param name
     */
    constructor(iso6391: String, name: String) : super() {
        this.iso6391 = iso6391
        this.name = name
    }

}

class Videos {

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param results
     */
    constructor(results: List<Result>) : super() {
        this.results = results
    }

}