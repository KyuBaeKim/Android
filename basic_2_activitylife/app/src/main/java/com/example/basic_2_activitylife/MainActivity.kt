package com.example.basic_2_activitylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var nLineNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            nLineNumber = savedInstanceState.getInt("Line_Number", 0)

        }

        Log.d(TAG, "${nLineNumber++} onCreate, $savedInstanceState")
    }


    override fun onRestart() {
        super.onRestart()

        Log.d(TAG, "${nLineNumber++} onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "${nLineNumber++} onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "${nLineNumber++} onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "${nLineNumber++} onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "${nLineNumber++} onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "${nLineNumber++} onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) { // bundle = (map or dictionary) 복원하는 값을 저장
        super.onSaveInstanceState(outState)
        Log.d(TAG, "${nLineNumber++} onSaveInstanceState")
        outState.putInt("Line_Number", nLineNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "${nLineNumber++} onRestoreInstanceState")

    }
}