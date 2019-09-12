package pl.arturostrowski.android.mvp.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.models.rx.response.DiscoverMovieResults
import pl.arturostrowski.android.mvp.app.util.Constants
import com.squareup.picasso.Picasso

class DiscoverSliderAdapter(private val list: MutableList<DiscoverMovieResults>, private val onClick: ((DiscoverMovieResults) -> Unit)? = null): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun getCount(): Int {
        return list.size
    }

    fun addItem(discoverMovieResults: DiscoverMovieResults){
        list.add(discoverMovieResults)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
                .inflate(R.layout.item_discover_slider, container,false)

        val ivItem: ImageView = view.findViewById(R.id.ivItem)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)

        Picasso.get() // give it the context
                    .load(Constants.THE_MOVIE_DB_IMAGES_URL+list[position].backdropPath) // load the image
                    .fit()
                    .into(ivItem)

        tvTitle.text = list[position].title

        onClick?.let {
            with(view) {
                setOnClickListener {
                    onClick.invoke(list[position])
                }
            }
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        parent.removeView(`object` as View)
    }

}