package pl.arturostrowski.android.mvp.app.ui.movieDetails;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.imageResource
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.di.component.DaggerFragmentComponent
import pl.arturostrowski.android.mvp.app.di.module.FragmentModule
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieCreditsResponse
import pl.arturostrowski.android.mvp.app.models.rx.response.MovieDetailsResponse
import pl.arturostrowski.android.mvp.app.ui.adapter.ActorsAdapter
import pl.arturostrowski.android.mvp.app.ui.adapter.CreatorsAdapter
import pl.arturostrowski.android.mvp.app.ui.adapter.MovieDetailGenreAdapter
import pl.arturostrowski.android.mvp.app.ui.main.MainActivity
import pl.arturostrowski.android.mvp.app.ui.video.VideoFragment
import pl.arturostrowski.android.mvp.app.util.Constants
import pl.arturostrowski.android.mvp.app.util.DateUtils
import pl.arturostrowski.android.mvp.app.util.showELog
import pl.arturostrowski.android.mvp.app.util.toast
import java.util.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment(), MovieDetailsContract.View {
    companion object {
        val TAG: String = MovieDetailsFragment::class.java.simpleName
        const val MOVIE_ID: String = "MOVIE_ID"

        fun newInstance(movieID: Long) = MovieDetailsFragment().apply {
            arguments = bundleOf(
                    MOVIE_ID to movieID
            )
        }
    }

    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter
    lateinit var listAdapter: ActorsAdapter
    lateinit var creatorsAdapter: CreatorsAdapter
    lateinit var genresAdapter: MovieDetailGenreAdapter
    var movieID: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_movie_details, container, false)

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
        (context as MainActivity).setTitle(getString(R.string.fragment_movie_datails_title))
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
        presenter.loadMovieDetails(movieID)
        presenter.loadMovieCredits(movieID)
    }

    override fun showMovieDetails(movieDetails: MovieDetailsResponse) {
        Picasso.get()
                .load(Constants.THE_MOVIE_DB_IMAGES_URL+movieDetails.backdropPath) // load the image
                .fit()
                .into(ivMovieDetails)

        tvVoteAverage.text = movieDetails.voteAverage.toString()
        tvTitle.text = movieDetails.title
        tvStorylineContent.text = movieDetails.overview
        tvOriginalTitleContent.text = movieDetails.originalTitle
        tvPremiereContent.text = movieDetails.releaseDate
        tvDescriptionContent.text = movieDetails.overview
        tvVoteCount.text = String.format(Locale.getDefault(), getString(R.string.fragment_movie_datails_vote_count), movieDetails.voteCount)
        tvTime.text = String.format(Locale.getDefault(), getString(R.string.fragment_movie_datails_time), movieDetails.runtime/60, movieDetails.runtime%60)
        tvReleaseDate.text = DateUtils().convertDate(movieDetails.releaseDate, DateUtils.YYYYMMDD, DateUtils.YYYY)
        ratingBar.rating = movieDetails.voteAverage.toFloat()/2

        movieDetails.genres.forEachIndexed { index, genre ->
            tvTypeContent.append(if(index+1 == movieDetails.genres.size) genre.name else "${genre.name}, ")
        }
        movieDetails.productionCompanies.forEachIndexed { index, company ->
            tvProductionContent.append(if(index+1 == movieDetails.productionCompanies.size) company.name else "${company.name}, ")
        }

        tvPlayTrailer.setOnClickListener {
            (context as MainActivity).switchContent(VideoFragment.newInstance(movieDetails.id), VideoFragment.TAG)
        }

        ivFavMovie.setOnClickListener {
            ivFavMovie.imageResource = if(ivFavMovie.drawable.constantState == ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_border_white)!!.constantState){
                R.drawable.ic_favorite_yellow
            }else{
                R.drawable.ic_favorite_border_white
            }
        }

        genresAdapter = MovieDetailGenreAdapter(movieDetails.genres){
        }
        recyclerViewGenres.apply {
            layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
            adapter = genresAdapter
        }
    }

    override fun showMovieCredits(movieCredits: MovieCreditsResponse) {
        listAdapter = ActorsAdapter(movieCredits.cast){
        }
        recyclerViewActors.apply {
            layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
            adapter = listAdapter
        }

        creatorsAdapter = CreatorsAdapter(movieCredits.crew){
        }
        recyclerViewCreators.apply {
            layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
            adapter = creatorsAdapter
        }
    }

    override fun showToast(text: String) {
        toast(text)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showErrorMessage(error: String) {
        showELog(error)
    }

}