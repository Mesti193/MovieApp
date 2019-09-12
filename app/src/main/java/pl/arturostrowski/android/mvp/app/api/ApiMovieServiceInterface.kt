package pl.arturostrowski.android.mvp.app.api

import pl.arturostrowski.android.mvp.app.models.rx.response.DiscoverMoviesResponse
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieCreditsResponse
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieDetailsResponse
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieVideoResponse
import pl.arturostrowski.android.mvp.app.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiMovieServiceInterface {

    @GET("movie/popular")
    fun getPopularMovie(@QueryMap query: Map<String, String>): Observable<DiscoverMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieID: Long, @QueryMap query: Map<String, String>): Observable<MovieDetailsResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") movieID: Long, @QueryMap query: Map<String, String>): Observable<MovieVideoResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieID: Long, @QueryMap query: Map<String, String>): Observable<MovieCreditsResponse>

    companion object Factory {
        fun create(): ApiMovieServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.THE_MOVIE_DB_API_URL)
                    .build()

            return retrofit.create(ApiMovieServiceInterface::class.java)
        }
    }
}