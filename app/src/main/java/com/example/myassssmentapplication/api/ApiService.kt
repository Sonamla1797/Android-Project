package com.example.myassssmentapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.myassssmentapplication.LoginRequest
import com.example.myassssmentapplication.LoginResponse
import com.example.myassssmentapplication.ui.dashboard.DashboardResponse



interface ApiService {
    @POST("sydney/auth")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("dashboard/{keypass}")
    fun getDashboard(@Path("keypass") keypass: String): Call<DashboardResponse>
}
