package pl.arturostrowski.android.mvp.app.ui.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_list.progressBar
import kotlinx.android.synthetic.main.fragment_list.recyclerView
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.di.component.DaggerFragmentComponent
import pl.arturostrowski.android.mvp.app.di.module.FragmentModule
import pl.arturostrowski.android.mvp.app.models.rx.response.DiscoverMovieResults
import pl.arturostrowski.android.mvp.app.ui.adapter.BestPopularAdapter
import pl.arturostrowski.android.mvp.app.ui.adapter.DiscoverSliderAdapter
import pl.arturostrowski.android.mvp.app.ui.main.MainActivity
import pl.arturostrowski.android.mvp.app.ui.movieDetails.MovieDetailsFragment
import pl.arturostrowski.android.mvp.app.ui.video.VideoFragment
import pl.arturostrowski.android.mvp.app.util.showELog
import javax.inject.Inject

class DashboardFragment: Fragment(), DashboardContract.View {
    companion object {
        val TAG: String = DashboardFragment::class.java.simpleName
        var currentPage = 0
        lateinit var update: Runnable
        val handler = Handler()
        fun newInstance() = DashboardFragment().apply {

        }
    }

    @Inject
    lateinit var presenter: DashboardContract.Presenter

    lateinit var listAdapter: BestPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onResume() {
        super.onResume()
        (context as MainActivity).setTitle(getString(R.string.fragment_dashboard_title))
        presenter.loadPopularMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    private fun initView() {
        showProgress(false)

    }

    override fun showPopularMovies(list: ArrayList<DiscoverMovieResults>) {
        val array: ArrayList<DiscoverMovieResults> = arrayListOf()

        listAdapter = BestPopularAdapter(array){
            (context as MainActivity).switchContent(MovieDetailsFragment.newInstance(it.id), MovieDetailsFragment.TAG)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
            adapter = listAdapter
        }

        list.forEach {
            listAdapter.addItem(it)
        }

        val discoverSliderAdapter = DiscoverSliderAdapter(list){
            (context as MainActivity).switchContent(VideoFragment.newInstance(it.id), VideoFragment.TAG)
        }

        vpDiscover.adapter = discoverSliderAdapter
        dots.attachViewPager(vpDiscover)
        startAutoSlidingPager(list.size, false)
    }

    private fun startAutoSlidingPager(size: Int, pause: Boolean){
        vpDiscover.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
        val numPages = size
        update = Runnable {
            if (currentPage == numPages) {
                currentPage = 0
            }
            vpDiscover?.setCurrentItem(currentPage++, true)
            if(pause){
                handler.removeCallbacks(update)
            }else{
                handler.postDelayed(update, 2000)
            }
        }
        handler.postDelayed(update, 1500)
    }

    override fun onPause() {
        super.onPause()
        startAutoSlidingPager(listAdapter.itemCount, true)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showErrorMessage(error: String) {
        showELog(error)
    }

}