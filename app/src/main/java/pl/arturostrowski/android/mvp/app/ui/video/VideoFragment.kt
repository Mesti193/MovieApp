package pl.arturostrowski.android.mvp.app.ui.video

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.di.component.DaggerFragmentComponent
import pl.arturostrowski.android.mvp.app.di.module.FragmentModule
import pl.arturostrowski.android.mvp.app.ui.main.MainActivity
import pl.arturostrowski.android.mvp.app.util.showELog
import kotlinx.android.synthetic.main.fragment_video.*
import org.jetbrains.anko.bundleOf
import javax.inject.Inject

class VideoFragment : Fragment(), VideoContract.View {
    companion object {
        val TAG: String = VideoFragment::class.java.simpleName
        const val MOVIE_ID: String = "MOVIE_ID"

        fun newInstance(movieID: Long) = VideoFragment().apply {
            arguments = bundleOf(
                    MOVIE_ID to movieID
            )
        }
    }

    @Inject
    lateinit var presenter: VideoContract.Presenter
    var movieID: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
//        activity!!.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        if (arguments?.containsKey(MOVIE_ID) == true) {
            movieID = arguments?.getLong(MOVIE_ID)!!
        }
        initView()
    }

    override fun onResume() {
        super.onResume()
        (context as MainActivity).showToolbar(false)
        (context as MainActivity).changeScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (context as MainActivity).changeScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        presenter.unsubscribe()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadVideo(movieID)
    }

    override fun showVideo(key: String) {
        webViewPlayer.settings.javaScriptEnabled = true
        webViewPlayer.loadUrl("https://www.youtube.com/embed/$key")
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showErrorMessage(error: String) {
        showELog(error)
    }

}