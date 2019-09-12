package pl.arturostrowski.android.mvp.app.models.rx.response

import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
        @SerializedName("id")
        val id: Long,
        @SerializedName("cast")
        val cast: ArrayList<Cast>,
        @SerializedName("crew")
        val crew: ArrayList<Crew>
)

data class Cast(
        @SerializedName("cast_id")
        val castID: Long,
        @SerializedName("character")
        val character: String,
        @SerializedName("credit_id")
        val creditID: String,
        @SerializedName("gender")
        val gender: Long,
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("order")
        val order: Long,
        @SerializedName("profile_path")
        val profilePath: String
)

data class Crew(
        @SerializedName("credit_id")
        val creditID: String,
        @SerializedName("department")
        val department: String,
        @SerializedName("gender")
        val gender: Long,
        @SerializedName("id")
        val id: Long,
        @SerializedName("job")
        val job: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_path")
        val profilePath: String
)