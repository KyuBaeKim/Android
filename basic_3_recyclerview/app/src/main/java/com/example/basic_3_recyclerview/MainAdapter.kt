package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(var items: MutableList<MainData>, val l: (Int) -> Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder>()
{

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            l(position)
        }
       items[position].let { item ->
           with(holder){
               tvTitle.text = item.title
               tvContent.text = item.content
               Glide.with(itemView)
                   .load(item.image)
                   .override(64, 64) // 메모리 이미지 크기조절
                   .into(imageView)

           }
       }
    }

    inner class MainViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                val tvTitle = itemView.tv_main_title
                val tvContent = itemView.tv_main_content
                val imageView = itemView.imageView
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)


        return MainViewHolder(view)
    }







    override fun getItemCount(): Int = items.size

}