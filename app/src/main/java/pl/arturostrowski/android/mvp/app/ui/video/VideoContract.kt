package pl.arturostrowski.android.mvp.app.ui.video

import pl.arturostrowski.android.mvp.app.ui.base.BaseContract

class VideoContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun showVideo(key: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadVideo(movieID: Long)
    }
}