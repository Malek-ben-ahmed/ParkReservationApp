package com.example.parkreservationapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("Signup")
    fun signup(@Body user: User): Call<Void>
    @GET("slots")
    fun getSlots(): Call<List<Slot>>
}
