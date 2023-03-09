package com.example.customerchatapp.api

import com.example.customerchatapp.model.CustomerInfoResponse
import com.example.customerchatapp.model.CustomerListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerAPI {
    @GET("users")
    fun getCustomerList(
        @Query("page") startIndex:Int)
            : Call<CustomerListResponse>

    @GET("users/{userId}/")
    fun getCustomerInfo(
        @Path("userId") userID:Int)
            : Call<CustomerInfoResponse>
}