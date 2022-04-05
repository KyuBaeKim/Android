package com.example.imagesearch

import android.util.Log
import com.example.imagesearch.data.image
import com.example.imagesearch.data.weather.CurrentWeather
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val APPID="376a73fb8c391aa2f729c342e2e962b0"

interface OpenWeatherService{
    @GET("data/2.5/weather?APPID=$APPID")
    fun getWeather(
        @Query("q") city: String,
        @Query("lang") lang: String = "kr",
        @Query("units") units: String = "metric"
    ): Call<CurrentWeather>
}

object OpenWeather{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(OpenWeatherService::class.java)

    fun getweather(city: String, lang:String="kr", units: String="meric",
                callback: (CurrentWeather)->Unit){
        service.getWeather(city, lang, units)
            .enqueue(object: Callback<CurrentWeather>{
                override fun onResponse(call: Call<CurrentWeather>, response: Response<CurrentWeather>){
                    if (response.isSuccessful) {
                        val data = response.body()
                        callback(data!!)
                    }
                }
                override fun onFailure(call: Call<CurrentWeather>, t:Throwable){
                    Log.e("---", t.toString())
                }
            })
    }
}