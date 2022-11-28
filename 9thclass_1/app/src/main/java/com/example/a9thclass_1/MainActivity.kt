package com.example.a9thclass_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a9thclass_1.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private  lateinit var viewBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://prodmp.eric-rc.shop/")    //사용할 서버의 base Url
            .addConverterFactory(GsonConverterFactory.create())
            .build()                                    //build까지 해줘야 작동. build-up pattern

        //retrofit 객체를 interface apiService와 연결해야 함
        //웹브라우저 주소입력 완료! 주소 내용은 ApiService 안에 있다고 보면 됨
        val apiService = retrofit.create(ApiService::class.java)

        //현재 서버에서는 같은 이메일이 있는지 확인하는 작업
        //queue에 넣는 작업
        //입력한 주소 중에 하나로 연결 시도!
        apiService.getCheckEmail("abc@abc.com").enqueue(object:Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) { //응답 받았을 때
                if (response.isSuccessful) {    //성공헀을 때
                    val responseData = response.body()
                    Log.d("Retrofit", "Response\nA${responseData?.code} Message : ${responseData?.message}")
                    if (responseData?.code == 3001) {    //Api 설명서 참고
                        Toast.makeText(this@MainActivity, "중복된 이메일입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Log.w("Retrofit","Response Not Successful ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {        //서버와 연결 실패했을 때
                Log.e("Retrofit","connecting Error", t)
            }

        })
    }
}