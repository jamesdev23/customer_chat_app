package com.example.customerchatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customerchatapp.adapter.CustomerListAdapter
import com.example.customerchatapp.api.CustomerAPIClient
import com.example.customerchatapp.databinding.ActivityMainBinding
import com.example.customerchatapp.model.CustomerList
import com.example.customerchatapp.model.CustomerListResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Firebase instance variables
    private lateinit var auth: FirebaseAuth

    private lateinit var customerListAdapter: CustomerListAdapter
    var customerList = ArrayList<CustomerList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth and check if the user is signed in
        auth = Firebase.auth
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        // list users
        customerListAdapter = CustomerListAdapter(this@MainActivity, customerList)

        binding.recyclerCustomerList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.recyclerCustomerList.adapter = customerListAdapter

        getData()

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in.
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
    }

    private fun getData(){
        val call: Call<CustomerListResponse> =
            CustomerAPIClient.getCustomerData.getCustomerList(1)

        call.enqueue(object : Callback<CustomerListResponse> {
            override fun onFailure(call: Call<CustomerListResponse>, t: Throwable) {
                Log.d("API CALL", "Failed API CALL")
            }

            override fun onResponse(
                call: Call<CustomerListResponse>,
                response: Response<CustomerListResponse>
            ) {
                var response: CustomerListResponse = response!!.body()!!

                customerListAdapter!!.setList(response.customer_list_data)

                var customerlist = response.customer_list_data
                for(customer in customerlist) {
                    Log.d("API CALL", "${customer.first_name} ${customer.last_name}")
                }
            }
        })
    }
}