package pl.arturostrowski.android.mvp.app.ui.movieDetails;

import pl.arturostrowski.android.mvp.app.api.ApiMovieServiceInterface
import pl.arturostrowski.android.mvp.app.models.rx.request.MovieCreditsRequest
import pl.arturostrowski.android.mvp.app.models.rx.request.MovieDetailsRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter : MovieDetailsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiMovieServiceInterface = ApiMovieServiceInterface.create()
    private lateinit var view: MovieDetailsContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun loadMovieDetails(movieID: Long) {
        val subscribe = api.getMovieDetails(movieID, MovieDetailsRequest().getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showMovieDetails(it)
                }, {
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

    override fun loadMovieCredits(movieID: Long) {
        val subscribe = api.getMovieCredits(movieID, MovieCreditsRequest().getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.showMovieCredits(it)
                }, {
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscriptions.add(subscribe)
    }
}