package com.zeroday.buupass

import com.google.gson.annotations.SerializedName

data class loginDetails(

    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("token") val token: String? = null
)