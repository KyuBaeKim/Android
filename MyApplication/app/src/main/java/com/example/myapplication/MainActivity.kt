package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.niqdev.mjpeg.DisplayMode
import com.github.niqdev.mjpeg.Mjpeg
import com.github.niqdev.mjpeg.MjpegInputStream
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception

const val PUB_TOPIC = "iot/camera/angle"
const val SERVER_URI = "tcp://192.168.219.168:1883"
const val SUB_TOPIC = "iot/#"


class MainActivity : AppCompatActivity() {

    lateinit var mqttClient: Mqtt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mqttClient = Mqtt(this, SERVER_URI)
        try {

            mqttClient.connect(arrayOf<String>(SUB_TOPIC))
        } catch (e: Exception) {
            e.printStackTrace()
        }


        btnSnapshot.setOnClickListener {
            mjpeg.visibility = View.INVISIBLE
            imageView.visibility = View.VISIBLE

            val url = "http://192.168.219.139:8000/mjpeg/snapshot"
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
                .open("http://192.168.219.139:8000/mjpeg/stream/", 5)
                .subscribe { inputStream: MjpegInputStream? ->
                    mjpeg.setSource(inputStream!!)

                    mjpeg.setDisplayMode(DisplayMode.BEST_FIT)
                }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(view: SeekBar?, value: Int, fromUser: Boolean) {
                if(fromUser) {
                    txtAngle.text = "SeekBar 값 $value"
                // SeekBar 값을 publish token으로 받기
                    mqttClient.publish(PUB_TOPIC, "$value")

                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
    fun onReceived(topic: String, message: MqttMessage) {
        val msg = String(message.payload)
        Log.i("Mqtt", "seekBar 값 :  $msg")
    }
    fun publish() {
        mqttClient.publish(PUB_TOPIC, "1")
    }

    override fun onPause() {
        super.onPause()
        if(mjpeg.isStreaming)
            mjpeg.stopPlayback()
    }
}