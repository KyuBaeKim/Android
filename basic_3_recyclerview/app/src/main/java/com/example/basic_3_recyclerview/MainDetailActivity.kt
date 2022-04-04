package com.example.basic_3_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_data.*
import kotlinx.android.synthetic.main.activity_main_detail.*

class MainDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)


        val data = intent.getParcelableExtra<MainData>("MAIN_DATA")

        txtTitle.text = data?.title
        txtContent.text = data?.content
    }
}