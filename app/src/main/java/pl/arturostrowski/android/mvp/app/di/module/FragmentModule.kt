package pl.arturostrowski.android.mvp.app.di.module

import pl.arturostrowski.android.mvp.app.api.ApiBaseServiceInterface
import pl.arturostrowski.android.mvp.app.api.ApiMovieServiceInterface
import pl.arturostrowski.android.mvp.app.ui.dashboard.DashboardContract
import pl.arturostrowski.android.mvp.app.ui.dashboard.DashboardPresenter
import pl.arturostrowski.android.mvp.app.ui.movieDetails.MovieDetailsContract
import pl.arturostrowski.android.mvp.app.ui.movieDetails.MovieDetailsPresenter
import pl.arturostrowski.android.mvp.app.ui.video.VideoContract
import pl.arturostrowski.android.mvp.app.ui.video.VideoPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideDashboardPresenter(): DashboardContract.Presenter {
        return DashboardPresenter()
    }

    @Provides
    fun provideMovieDetailsPresenter(): MovieDetailsContract.Presenter {
        return MovieDetailsPresenter()
    }

    @Provides
    fun provideVideoPresenter(): VideoContract.Presenter {
        return VideoPresenter()
    }


    @Provides
    fun provideApiService(): ApiMovieServiceInterface {
        return ApiMovieServiceInterface.create()
    }

    @Provides
    fun provideApiBaseService(): ApiBaseServiceInterface {
        return ApiBaseServiceInterface.create()
    }
}