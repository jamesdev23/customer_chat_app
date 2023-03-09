package com.example.customerchatapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customerchatapp.ChatActivity
import com.example.customerchatapp.R
import com.example.customerchatapp.databinding.ItemCustomerListBinding
import com.example.customerchatapp.model.CustomerList
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class CustomerListAdapter (private var context: Context,
    private var customers: ArrayList<CustomerList>)
: RecyclerView.Adapter<CustomerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val itemBinding = ItemCustomerListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = customers.size

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        holder.bindItems(customers[position])

        holder.btn_chat.setOnClickListener {
            var fullName = "${customers[position].first_name} ${customers[position].last_name}"
            Log.d("Customer", fullName)

//            Intent().also{
//                Log.d("Customer ID", "${customers[position].iD}")
//
//                it.action = "ph.kodego.md2p.GETDATA"
//                it.putExtra("data", customers[position].iD)
//                context.sendBroadcast(it)
//            }
            Intent(context, ChatActivity::class.java).also { intent ->
                Log.d("Customer ID", "${customers[position].iD}")
                intent.putExtra("data", customers[position].iD)
                context.sendBroadcast(intent)

                // start the ChatActivity
                context.startActivity(intent)
            }
        }



    }

    fun setList(usersList: ArrayList<CustomerList>){
        this.customers.clear()
        this.customers.addAll(usersList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemCustomerListBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        public var btn_chat = itemBinding.btnChat

        fun bindItems(customerList: CustomerList) {
            var imageUrl = customerList.avatar_url
            var fullName = "${customerList.first_name} ${customerList.last_name}"


            imageUrl?.let{
                Picasso
                    .with(itemView.context)
                    .load(it)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile)
                    .into(itemBinding!!.customerlistAvatar)
            }
            itemBinding.customerlistName.text = fullName
        }
    }

}