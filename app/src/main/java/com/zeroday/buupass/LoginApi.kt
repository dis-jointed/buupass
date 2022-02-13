package com.zeroday.buupass

import com.zeroday.buupass.model.UserResp
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @FormUrlEncoded
    @POST("api/login")
    fun signInUser(
        @Field("email") email: String,
        @Field("password") password: String? = null,
    ): Call<LogInResponse>

    @GET("api/users/2")
    fun getUserDetails(): Call<UserResp>
}