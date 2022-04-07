package com.example.tabedex.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabedex.Mqtt
import com.example.tabedex.databinding.FragmentDeviceControlBinding
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.lang.Exception

private const val SUB_TOPIC = "iot/#"
private const val PUB_TOPIC = "iot/led"
private const val SERVER_URI = "tcp://172.30.1.30:1883"

class DeviceControlFragment : Fragment() {
    private var _binding: FragmentDeviceControlBinding? = null
    private val binding get() = _binding!!

    lateinit var mqttClient: Mqtt

    // Fragment가 Activity에 연결될 때 호출되는 이벤트 핸들러
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mqttClient = Mqtt(context, SERVER_URI)

        try{
            mqttClient.setCallback(::onReceived)
            mqttClient.connect(arrayOf<String>(SUB_TOPIC))
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    // _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeviceControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchLed.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {// switch on
                mqttClient.publish(PUB_TOPIC, "on")
            } else {//switch off
                mqttClient.publish(PUB_TOPIC, "off")
            }
        }
    }



    fun onReceived(topic: String, message: MqttMessage) {
        val msg = String(message.payload)
        Log.i("Mqtt", "수신 메세지 :  $msg")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}