package pl.arturostrowski.android.mvp.app.ui.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
//        view.showLoginFragment() // as default
        view.showDashboardFragment() // as default
    }

    override fun onDrawerOptionAboutClick() {
//        view.showLoginFragment()
    }

}