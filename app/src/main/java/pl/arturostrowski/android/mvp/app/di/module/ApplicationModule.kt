package pl.arturostrowski.android.mvp.app.di.module

import android.app.Application
import pl.arturostrowski.android.mvp.app.BaseApp
import pl.arturostrowski.android.mvp.app.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}