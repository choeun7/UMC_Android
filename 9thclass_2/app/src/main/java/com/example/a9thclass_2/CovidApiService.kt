package com.example.a9thclass_2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidApiService {
    @GET ("15077586/v1/centers")
    fun getCovidInfo(
        @Query("page") page : Int,
        @Query("perPage") perPage : Int,
        @Query("serviceKey") serviceKey : String
    ): Call<CenterCovidApiResponse>
}