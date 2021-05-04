package io.github.codejunk1e.maythefourth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.codejunk1e.maythefourth.R
import io.github.codejunk1e.maythefourth.domain.User
import io.github.codejunk1e.maythefourth.responsemodels.PeopleResponse

/*
 * @author robin
 */
class PeopleAdapter(val callback: (User, String) -> Unit,):
    PagedListAdapter<PeopleResponse, PeopleAdapter.ViewHolder>(
        object: DiffUtil.ItemCallback<PeopleResponse> (){
            override fun areItemsTheSame(oldItem: PeopleResponse , newItem: PeopleResponse): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: PeopleResponse, newItem: PeopleResponse): Boolean {
                return oldItem.name == newItem.name
            }
        }
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response: PeopleResponse? = getItem(position);
        if (response != null){
            holder.bind(response, position)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            item: PeopleResponse,
            position: Int
        ) {
            val itemImage = itemView.findViewById<ImageView>(R.id.itemImage)
            val itemText = itemView.findViewById<TextView>(R.id.itemTitle)
            itemImage.setImageResource(R.drawable.dp)
            itemText.text = item.name

            val image = if(position > 15) "https://starwars-visualguide.com/assets/img/characters/${position + 2}.jpg"
            else "https://starwars-visualguide.com/assets/img/characters/${position + 1}.jpg"


            itemView.setOnClickListener {
                val user = User( item.name, item.gender, item.height )
                callback(user, image)
            }

            if (position > 15) {
                Glide.with(itemView.context)
                    .load(image)
                    .override(350, 400)
                    .error(R.drawable.big_placeholder)
                    .into(itemImage)
            }
            else{
                Glide.with(itemView.context)
                    .load(image)
                    .override(350, 400)
                    .error(R.drawable.big_placeholder)
                    .into(itemImage)
            }
        }
    }
}