package pl.arturostrowski.android.mvp.app

import android.app.Application
import pl.arturostrowski.android.mvp.app.di.component.ApplicationComponent
import pl.arturostrowski.android.mvp.app.di.component.DaggerApplicationComponent
import pl.arturostrowski.android.mvp.app.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}