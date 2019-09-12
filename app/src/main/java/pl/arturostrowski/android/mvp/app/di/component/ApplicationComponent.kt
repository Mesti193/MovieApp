package pl.arturostrowski.android.mvp.app.di.component

import pl.arturostrowski.android.mvp.app.BaseApp
import pl.arturostrowski.android.mvp.app.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}