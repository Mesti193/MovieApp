package pl.arturostrowski.android.mvp.app.ui.dashboard

import pl.arturostrowski.android.mvp.app.models.rx.response.DiscoverMovieResults
import pl.arturostrowski.android.mvp.app.ui.base.BaseContract

class DashboardContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showPopularMovies(list: ArrayList<DiscoverMovieResults>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadPopularMovies()
    }
}