package pl.arturostrowski.android.mvp.app.di.module

import android.app.Activity
import pl.arturostrowski.android.mvp.app.ui.main.MainContract
import pl.arturostrowski.android.mvp.app.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}