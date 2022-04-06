package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.niqdev.mjpeg.DisplayMode
import com.github.niqdev.mjpeg.Mjpeg
import com.github.niqdev.mjpeg.MjpegInputStream
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSnapshot.setOnClickListener {
            val url = "http://192.168.0.30:8000/mjpeg/snapshot"
            Glide.with(this)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
        }
        btnStream.setOnClickListener {
            mjpeg.visibility = View.VISIBLE
            imageView.visibility = View.INVISIBLE

            Mjpeg.newInstance()
                .open("http://192.168.0.30:8000/mjpeg/stream/", 5)
                .subscribe { inputStream: MjpegInputStream? ->
                    mjpeg.setSource(inputStream!!)

                    mjpeg.setDisplayMode(DisplayMode.BEST_FIT)
                }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(view: SeekBar?, value: Int, fromUser: Boolean) {
                if(fromUser) {
                    txtAngle.text = "SeekBar 값 $value"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun onPause() {
        super.onPause()
        if(mjpeg.isStreaming)
            mjpeg.stopPlayback()
    }
}