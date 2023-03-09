package com.example.customerchatapp

fun String.getCustomerID() : Int{
    var value = this.toString()
    val id = value.substring(34, value.length-1)
    return id.toInt()
}