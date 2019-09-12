package pl.arturostrowski.android.mvp.app.models.rx.response

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
//        @SerializedName("belongs_to_collection")
//        val page: Long,
        @SerializedName("budget")
        val budget: Long,
        @SerializedName("genres")
        val genres: ArrayList<Genre>,
        @SerializedName("homepage")
        val homepage: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("imdb_id")
        val imdbID: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("production_companies")
        val productionCompanies: ArrayList<ProductionCompanies>,
//        @SerializedName("production_countries")
//        val page: Long,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("revenue")
        val revenue: Long,
        @SerializedName("runtime")
        val runtime: Long,
//        @SerializedName("spoken_languages")
//        val page: Long,
        @SerializedName("status")
        val status: String,
        @SerializedName("tagline")
        val tagline: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Long
){
        override fun toString(): String {
                return "MovieDetailsResponse(adult=$adult, backdropPath='$backdropPath', budget=$budget, genres=$genres, homepage='$homepage', id=$id, imdbID='$imdbID', originalLanguage='$originalLanguage', originalTitle='$originalTitle', overview='$overview', popularity=$popularity, posterPath='$posterPath', releaseDate='$releaseDate', revenue=$revenue, runtime=$runtime, status='$status', tagline='$tagline', title='$title', video=$video, voteAverage=$voteAverage, voteCount=$voteCount)"
        }
}

data class Genre(
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String
)

data class ProductionCompanies(
        @SerializedName("id")
        val id: Long,
        @SerializedName("logo_path")
        val logoPath: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String

)