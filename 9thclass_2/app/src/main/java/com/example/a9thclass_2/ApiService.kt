package com.example.a9thclass_2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("app/users/email-check")
    fun getCheckEmail(
        @Query("email") email: String
    ): Call<Response>
}