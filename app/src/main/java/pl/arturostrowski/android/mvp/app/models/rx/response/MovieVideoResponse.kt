package pl.arturostrowski.android.mvp.app.models.rx.response

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
        @SerializedName("id")
        val id: Long,
        @SerializedName("results")
        val result: ArrayList<MovieVideoDetails>
)

data class MovieVideoDetails(
        @SerializedName("id")
        val id: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("key")
        val key: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("site")
        val site: String,
        @SerializedName("size")
        val size: Long,
        @SerializedName("type")
        val type: String
)