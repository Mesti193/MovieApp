package pl.arturostrowski.android.mvp.app.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie_details_genre.view.*
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.models.rx.response.Genre
import pl.arturostrowski.android.mvp.app.util.inflate

class MovieDetailGenreAdapter(private val list: MutableList<Genre>, private val onClick: ((Genre) -> Unit)? = null): RecyclerView.Adapter<MovieDetailGenreAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItem(discoverMovieResults: Genre){
        list.add(discoverMovieResults)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        (holder as ListViewHolder).bind(list[position], onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder = ListViewHolder(parent.inflate(R.layout.item_movie_details_genre, false))

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Genre, onClick: ((Genre) -> Unit)?) {
            itemView.tvGenreName.text = item.name
        }
    }
}