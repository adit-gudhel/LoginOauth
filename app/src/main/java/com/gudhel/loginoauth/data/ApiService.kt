package com.gudhel.loginoauth.data

import com.gudhel.loginoauth.data.requests.LoginRequest
import com.gudhel.loginoauth.data.responses.LoginResponse
import com.gudhel.loginoauth.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}