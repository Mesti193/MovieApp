package pl.arturostrowski.android.mvp.app.di.component

import pl.arturostrowski.android.mvp.app.di.module.FragmentModule
import pl.arturostrowski.android.mvp.app.ui.dashboard.DashboardFragment
import pl.arturostrowski.android.mvp.app.ui.movieDetails.MovieDetailsFragment
import pl.arturostrowski.android.mvp.app.ui.video.VideoFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(dashboardFragment: DashboardFragment)

    fun inject(movieDetailsFragment: MovieDetailsFragment)

    fun inject(videoFragment: VideoFragment)

}