package pl.arturostrowski.android.mvp.app.ui.main

import pl.arturostrowski.android.mvp.app.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun setTitle(title: String)
        fun showToolbar(show: Boolean)
        fun changeScreenOrientation(screenOrientation: Int)
        fun showDashboardFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onDrawerOptionAboutClick()
    }
}