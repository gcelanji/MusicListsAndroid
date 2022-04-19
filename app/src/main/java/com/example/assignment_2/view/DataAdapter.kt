package com.example.assignment_2.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_2.R
import com.example.assignment_2.model.TrackItem
import com.squareup.picasso.Picasso

class DataAdapter (private val dataSet : List<TrackItem>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private val image : ImageView = view.findViewById(R.id.track_image)
        private val  collectionName : TextView = view.findViewById(R.id.tv_collection)
        private val artistName : TextView = view.findViewById(R.id.tv_artist)
        private val price : TextView = view.findViewById(R.id.tv_price)

        fun onBind(dataItem : TrackItem){
            Picasso.get().load(dataItem.artworkUrl100).into(this.image)
            collectionName.text = dataItem.collectionName
            artistName.text = dataItem.artistName
            price.text = dataItem.trackPrice.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.track_item_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       return holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}