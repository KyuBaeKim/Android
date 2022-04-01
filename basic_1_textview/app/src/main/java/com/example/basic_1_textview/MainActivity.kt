package com.example.basic_1_textview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtNormal.setOnClickListener {
            txtNormal.apply {
                setBackgroundColor(Color.RED)
                text = "Clicked!! ${nCount++}"
                setTextColor(Color.WHITE)
                setTextSize(28.0F)
            }
        }

        txtHTML.setOnClickListener {
            val htmlText = it as TextView
            htmlText.text = Html.fromHtml(
                "<h1>Hi</h1>Html<p style=\"color:red;\">Red</idv>"
            )

        }





    }
}