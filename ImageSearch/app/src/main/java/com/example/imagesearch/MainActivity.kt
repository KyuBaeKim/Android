package com.example.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.data.Document
import com.example.imagesearch.data.Image
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val imageList = mutableListOf<Document>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageListView.adapter = ImageListAdapter(imageList, ::onItemClick)
        imageListView.layoutManager = GridLayoutManager(this, 3)

        btnSearch.setOnClickListener{
            val keyword = editKeyword.text.toString()
            searchImage(keyword)
            editKeyword.setText("")
        }
    }

    private fun searchImage(keyword: String){
        KakaoImageSearch.search(keyword){
            imageList.addAll(it!!.documents)
            imageListView.adapter?.notifyDataSetChanged()
        }

//    private fun searchImage(keyword: String) {
//        KakaoImageSearch.getService()
//            .requestSearchImage(keyword = keyword, page = 1)
//            .enqueue(object :retrofit2.Callback<Image> {
//
//                override fun onFailure(call: retrofit2.Call<Image>, t: Throwable) {
//                    Log.e("----", t.toString())
//                }
//
//                override fun onResponse(call: retrofit2.Call<Image>, response: Response<Image>) {
//                    if(response.isSuccessful){
//                        val image = response.body()
//                        imageList.addAll(image!!.documents)
//                        imageListView.adapter?.notifyDataSetChanged()
//                    }
//                }
//
//
//
//            })

    }

    private fun onItemClick(document: Document) {
        TODO("Not yet implemented")
    }
}