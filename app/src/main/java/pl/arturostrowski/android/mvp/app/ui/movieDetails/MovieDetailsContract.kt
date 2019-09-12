package pl.arturostrowski.android.mvp.app.ui.movieDetails;

import pl.arturostrowski.android.mvp.app.models.rx.response.MovieCreditsResponse
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieDetailsResponse
import pl.arturostrowski.android.mvp.app.ui.base.BaseContract

class MovieDetailsContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showToast(text: String)
        fun showMovieDetails(movieDetails: MovieDetailsResponse)
        fun showMovieCredits(movieCredits: MovieCreditsResponse)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadMovieDetails(movieID: Long)
        fun loadMovieCredits(movieID: Long)
    }
}