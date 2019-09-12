package pl.arturostrowski.android.mvp.app.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.models.rx.response.DiscoverMovieResults
import pl.arturostrowski.android.mvp.app.util.Constants
import pl.arturostrowski.android.mvp.app.util.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_best_popular.view.*

class BestPopularAdapter(private val list: MutableList<DiscoverMovieResults>, private val onClick: ((DiscoverMovieResults) -> Unit)? = null): RecyclerView.Adapter<BestPopularAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItem(discoverMovieResults: DiscoverMovieResults){
        list.add(discoverMovieResults)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        (holder as ListViewHolder).bind(list[position], onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder = ListViewHolder(parent.inflate(R.layout.item_best_popular, false))

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DiscoverMovieResults, onClick: ((DiscoverMovieResults) -> Unit)?) {
            itemView.tvTitle.text = item.title
            itemView.tvVoteAverage.text = item.voteAverage.toString()
            itemView.ratingBar.rating = item.voteAverage.toFloat()/2

            Picasso.get() // give it the context
                    .load(Constants.THE_MOVIE_DB_IMAGES_URL+item.posterPath) // load the image
                    .fit()
                    .into(itemView.ivBestPopular)

            onClick?.let {
                with(itemView) {
                    setOnClickListener {
                        onClick.invoke(item)
                    }
                }
            }
        }
    }
}