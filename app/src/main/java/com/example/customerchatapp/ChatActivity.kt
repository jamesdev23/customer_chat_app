package com.example.customerchatapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.customerchatapp.api.CustomerAPIClient
import com.example.customerchatapp.databinding.ActivityChatBinding
import com.example.customerchatapp.model.CustomerInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            var message: String? = intent!!.getStringExtra("data")

            Log.i("Pokemon Info", message!!.toString())

            message?.let {
                getData(message.getCustomerID())
            }

            binding.customerId.setText(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupReceiver(applicationContext)
    }

    private fun setupReceiver(context: Context){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.md2p.GETDATA")
        context.registerReceiver(receiver, intentFilter)
    }

    private fun getData(id: Int){
        val call: Call<CustomerInfoResponse> = CustomerAPIClient.getCustomerListData.getCustomerInfo(id)

        call.enqueue(object : Callback<CustomerInfoResponse> {
            override fun onFailure(call: Call<CustomerInfoResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(
                call: Call<CustomerInfoResponse>,
                response: Response<CustomerInfoResponse>
            ) {
                var response: CustomerInfoResponse = response!!.body()!!


//                getCustomerInfos(response)

                Log.d("API INFO CALL", response.customer_data.toString())
            }
        })
    }
}