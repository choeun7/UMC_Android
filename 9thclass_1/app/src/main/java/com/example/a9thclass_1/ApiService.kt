package com.example.a9thclass_1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Alt+enter로 가져오기
    //뒤에 query string의 key=value가 있다면 둘 다 빼고 도메인 작성해야 함
    @GET("app/users/email-check")   //예시 도메인, 맨 앞 /도 빼야 함
    fun getCheckEmail (
        @Query("email") email : String
    ): Call<Response>   //종류에 상관없이 Call은 필수
}