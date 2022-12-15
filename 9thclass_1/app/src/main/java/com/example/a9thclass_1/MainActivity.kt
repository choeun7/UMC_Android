package com.example.a9thclass_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.a9thclass_1.databinding.ActivityMainBinding
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

        //어떤 주소를 입력했다!
        val apiService = retrofit.create(ApiService::class.java)

        //입력한 주소 중에 하나로 연결 시도
        apiService.getCheckEmail("test@test.com").enqueue(object : Callback<Response> {
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
    }
}