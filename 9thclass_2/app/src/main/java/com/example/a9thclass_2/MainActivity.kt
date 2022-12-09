package com.example.a9thclass_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.a9thclass_2.ApiService
import com.example.a9thclass_2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //웹브라우저 창 열기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prodmp.eric-rc.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val covidRetrofit = Retrofit.Builder()
            .baseUrl("https://api.odcloud.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //어떤 주소를 입력했다!
        val apiService = retrofit.create(ApiService::class.java)
        val covidApiService = covidRetrofit.create(CovidApiService::class.java)

        //입력한 주소 중에 하나로 연결 시도
        apiService.getCheckEmail("abc@abc.com").enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    if(responseData != null) {
                        Log.d("Retrofit","Response\nCode: ${responseData.code} Message : ${responseData.message}")
                        if (responseData.code == 3001) {
                            Toast.makeText(this@MainActivity, "중복된 이메일입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else {
                    Log.w("Retrofit","Response Not Successful ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Retrofit","Error!", t)
            }
        })

        //디코딩
        covidApiService.getCovidInfo(1,10,"HvRPOVFXKE0TTTiYY3IjEXQTpboDUr7suTcQ56YSB1QNfxZ7WDlSXUdzgLTpQkNWGbLAu4EM9Gu+XPVHE6WZEQ==")
            .enqueue(object : Callback<CenterCovidApiResponse> {
                override fun onResponse(call : Call<CenterCovidApiResponse>, response: retrofit2.Response<CenterCovidApiResponse>) {
                    if(response.isSuccessful) {
                        val responseData = response.body()

                        if(responseData != null) {
                            Log.d("Retrofit2", "${response.body()}")
                        }
                        else{
                            Log.d("Retrofit2","responseData == null")
                        }
                    }
                    else {
                        Log.w("Retrofit","Response Not Successful ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<CenterCovidApiResponse>, t: Throwable) {
                    Log.e("Retrofit2","Error!", t)
                }
            })
        //https://api.odcloud.kr/api/15077586/v1/centers?perPage=10&page=1&returnType=JSON&serviceKey=HvRPOVFXKE0TTTiYY3IjEXQTpboDUr7suTcQ56YSB1QNfxZ7WDlSXUdzgLTpQkNWGbLAu4EM9Gu%2BXPVHE6WZEQ%3D%3D
    }
}