package pl.arturostrowski.android.mvp.app.ui.dashboard

import pl.arturostrowski.android.mvp.app.api.ApiMovieServiceInterface
import pl.arturostrowski.android.mvp.app.models.rx.request.DiscoverMoviesRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardPresenter: DashboardContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiMovieServiceInterface = ApiMovieServiceInterface.create()
    private lateinit var view: DashboardContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DashboardContract.View) {
        this.view = view
    }

    override fun loadPopularMovies() {
        val subscribe = api.getPopularMovie(DiscoverMoviesRequest(page = 1).getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showPopularMovies(it.result)
//                    view.showMovieGenres(it)
                }, {
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

}