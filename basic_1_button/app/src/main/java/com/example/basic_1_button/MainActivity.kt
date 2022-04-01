package com.example.basic_1_button

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ID값으로 Control 변수를 사용. java style
        btn1.setOnClickListener {
            btn1.apply {
                text = "Click~!"
                setTextColor(Color.parseColor("#444444"))
                setBackgroundColor(Color.parseColor("#3333FF"))

            }
        }

            // ID값으로 Control 변수를 사용. java style
            btn2.setOnClickListener {
                btn2.apply {
                    text = "Click~!"
                    setTextColor(Color.parseColor("#333333"))
                    setBackgroundColor(Color.parseColor("#FFFF33"))

                }
            }


        }
    }

