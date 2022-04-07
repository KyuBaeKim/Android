package com.example.tabedex.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tabedex.Mqtt
import com.example.tabedex.R
import com.example.tabedex.databinding.FragmentSecureCameraBinding
import com.example.tabedex.databinding.FragmentTestBinding
import com.github.niqdev.mjpeg.DisplayMode
import com.github.niqdev.mjpeg.Mjpeg
import com.github.niqdev.mjpeg.MjpegInputStream
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception

private const val SUB_TOPIC = "iot/camera/angle"
private const val SERVER_URI = "tcp://172.30.1.30:1883"

class SecureCameraFragment : Fragment() {
    private var _binding: FragmentSecureCameraBinding? = null
    private val binding get() = _binding!!

    lateinit var mqttClient: Mqtt

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mqttClient = Mqtt(context, SERVER_URI)

        try {
            mqttClient.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecureCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSnapshot.setOnClickListener {
            binding.mjpeg.visibility = View.INVISIBLE
            binding.imageView.visibility = View.VISIBLE

            val url = "http://192.168.219.139:8000/mjpeg/snapshot"

            Glide.with(this)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.imageView)
        }

        binding.btnStream.setOnClickListener {
            binding.mjpeg.visibility = View.VISIBLE
            binding.imageView.visibility = View.INVISIBLE

            Mjpeg.newInstance()
                .open("http://192.168.219.139:8000/mjpeg/stream/", 5)
                .subscribe { inputStream: MjpegInputStream? ->
                    binding.mjpeg.setSource(inputStream!!)

                    binding.mjpeg.setDisplayMode(DisplayMode.BEST_FIT)
                }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(view: SeekBar?, value: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.txtAngle.text = "SeekBar 값 $value"
                    // SeekBar 값을 publish token으로 받기
                    //mqttClient.publish(PUB_TOPIC, "$value")

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
//            mqttClient.publish(PUB_TOPIC, "1")
        }

        override fun onPause() {
            super.onPause()
            if (binding.mjpeg.isStreaming)
                binding.mjpeg.stopPlayback()
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
