package pl.arturostrowski.android.mvp.app.ui.video

import pl.arturostrowski.android.mvp.app.api.ApiMovieServiceInterface
import pl.arturostrowski.android.mvp.app.models.rx.request.MovieVideoRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class VideoPresenter : VideoContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiMovieServiceInterface = ApiMovieServiceInterface.create()
    private lateinit var view: VideoContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: VideoContract.View) {
        this.view = view
    }

    override fun loadVideo(movieID: Long) {
        val subscribe = api.getMovieVideos(movieID, MovieVideoRequest().getQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    if(it.result.size > 0){
                        view.showVideo(it.result[0].key)
                    }else{
                        view.showErrorMessage("0 result :C")
                    }
                }, {
                    view.showProgress(false)
                    view.showErrorMessage(it.localizedMessage)
                })
        subscriptions.add(subscribe)
    }

}