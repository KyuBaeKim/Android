package com.example.imagesearch


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.data.Document

class ImageListAdapter(val imageList: List<Document>,
                       val onItemClick : (doc: Document)-> Unit)
    : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(doc: Document){
            Glide.with(imageView).load(doc.thumbnailUrl).into(imageView as ImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view as ImageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doc = imageList[position]
        holder.bind(doc)
        holder.imageView.setOnClickListener {
            onItemClick(doc)
        }
    }
    override fun getItemCount(): Int = imageList.size
}
