package com.example.customerchatapp.model

import com.google.gson.annotations.SerializedName

class CustomerList {
    @SerializedName("id")
    var iD: Int = -1

    @SerializedName("email")
    var email = ""

    @SerializedName("first_name")
    var first_name = ""

    @SerializedName("last_name")
    var last_name = ""

    @SerializedName("avatar")
    var avatar_url = ""

    constructor(firstname: String, lastname: String, url: String) {
        this.first_name = firstname
        this.last_name = lastname
        this.avatar_url = url
    }
}

class CustomerListResponse {
    @SerializedName("page")
    var page: Int = -1

    @SerializedName("per_page")
    var per_page: Int = -1

    @SerializedName("total")
    var total: Int = -1

    @SerializedName("total_pages")
    var total_pages: Int = -1

    @SerializedName("data")
    var customer_list_data: ArrayList<CustomerList> = ArrayList<CustomerList>()

    @SerializedName("support")
    var support: Support = Support()
}

class Customer {
    @SerializedName("id")
    var iD: Int = -1

    @SerializedName("email")
    var email = ""

    @SerializedName("first_name")
    var first_name = ""

    @SerializedName("last_name")
    var last_name = ""

    @SerializedName("avatar")
    var avatar_url = ""
}

class CustomerInfoResponse {
    @SerializedName("data")
    var customer_data:Customer = Customer()

    @SerializedName("support")
    var support:Support = Support()
}

class Support {
    @SerializedName("url")
    var support_url = ""

    @SerializedName("text")
    var support_text = ""
}