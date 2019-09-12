package pl.arturostrowski.android.mvp.app.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_actors.view.*
import pl.arturostrowski.android.mvp.app.R
import pl.arturostrowski.android.mvp.app.models.rx.response.Cast
import pl.arturostrowski.android.mvp.app.util.Constants
import pl.arturostrowski.android.mvp.app.util.inflate

class ActorsAdapter(private val list: MutableList<Cast>, private val onClick: ((Cast) -> Unit)? = null): RecyclerView.Adapter<ActorsAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItem(discoverMovieResults: Cast){
        list.add(discoverMovieResults)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        (holder as ListViewHolder).bind(list[position], onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder = ListViewHolder(parent.inflate(R.layout.item_actors, false))

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Cast, onClick: ((Cast) -> Unit)?) {
            Picasso.get() // give it the context
                    .load(Constants.THE_MOVIE_DB_IMAGES_URL+item.profilePath) // load the image
                    .fit()
                    .into(itemView.ivActor)
            itemView.tvName.text = item.name
            itemView.tvJob.text = item.character
        }
    }
}