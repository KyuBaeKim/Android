package com.example.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.imagesearch.data.Document
import com.example.imagesearch.data.image
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val imageList = mutableListOf<Document>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageListView.adapter = ImageListAdapter(imageList, ::onItemClick)
        imageListView.layoutManager = GridLayoutManager(this, 3)
        btnSearch.setOnClickListener {
            val keyword = editKeyword.text.toString()
            searchImage(keyword)
            editKeyword.setText("")

        }

        OpenWeather.getweather("seoul"){
            val iconCode = it.weather[0].icon
            val iconUrl = "http://openweathermap.org/img/wn/$iconCode@2x.png"
            val weather = "${it.weather[0].description} 온도 ${it.main.temp}도 / 습도 ${it.main.humidity}%"

            Glide.with(this).load(iconUrl).into(icon)
            txtWeather.text = weather

            Log.d("CurrentWeather", it.toString())
        }
    }
    private fun searchImage(keyword: String){
        KakaoImageSearch.search(keyword){
            imageList.addAll(it!!.documents)
            imageListView.adapter?.notifyDataSetChanged()
        }
    }
//    private fun searchImage(keyword: String) {
//        KakaoImageSearch.getService()
//            .requestSearchImage(keyword = keyword, page = 1)
//            .enqueue(object : Callback<image> {
//                override fun onFailure(call: Call<image>, t: Throwable) {
//                    Log.e("----", t.toString())
//                }
//
//                override fun onResponse(
//                    call: Call<image>,
//                    response: Response<image>
//                ) {
//                    if (response.isSuccessful) {
//                        val image = response.body()
//                        imageList.addAll(image!!.documents)
//                        imageListView.adapter?.notifyDataSetChanged()
//                    }
//                }
//            })
//    }


    fun onItemClick(document: Document) {

    }
}

