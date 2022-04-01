package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>()
{
    var items: MutableList<MainData> = mutableListOf(
                                MainData("Title1","Content1"),
                                MainData("Title2","Content2"),
                                MainData("Title3","Content3"),
    )
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       items[position].let { item ->
           with(holder){
               tvTitle.text = item.title
               tvContent.text = item.content
           }
       }
    }

    inner class MainViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                val tvTitle = itemView.tv_main_title
                val tvContent = itemView.tv_main_content
            }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)

    }



    override fun getItemCount(): Int = items.size

}