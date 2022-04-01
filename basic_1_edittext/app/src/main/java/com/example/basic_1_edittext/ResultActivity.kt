package com.example.basic_1_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.textMessage
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    override fun onStart() {
        super.onStart()

        // 값이 없으면 리턴
        val i = intent ?: return  // 호출에 사용된 Intent

        val sID = i.getStringExtra(MainActivity.ID)
        val sPasswd = i.getStringExtra(MainActivity.PASSWD)

        textMessage.text = "아이디: ${sID}\n패스워드: ${sPasswd}"
        i.putExtra(MainActivity.RESULT, textMessage.text.toString())

//        setResult(MainActivity.REQUEST, i)

        btnOk.setOnClickListener {
            setResult(MainActivity.REQUEST, i)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()

        }

    }
}