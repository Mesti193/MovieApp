package pl.arturostrowski.android.mvp.app.models.rx.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
        @SerializedName("page")
        val page: Long,
        @SerializedName("total_results")
        val totalResults: Long,
        @SerializedName("total_pages")
        val totalPages: Long,
        @SerializedName("results")
        val result: ArrayList<DiscoverMovieResults>
){
        override fun toString(): String {
                return "DiscoverMoviesResponse(page=$page, totalResults=$totalResults, totalPages=$totalPages, result=$result)"
        }
}

data class DiscoverMovieResults(
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("vote_count")
        val voteCount: Long,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: ArrayList<Long>,
        @SerializedName("title")
        val title: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String
)