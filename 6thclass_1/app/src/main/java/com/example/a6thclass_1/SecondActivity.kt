package com.example.a6thclass_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.a6thclass_1.databinding.ActivitySecondBinding
import com.google.android.material.tabs.TabLayoutMediator

class SecondActivity : AppCompatActivity() {
    private val viewBinding : ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        Log.d("oncreate","액티비티")

        val secondVPAdapter = SecondVPAdapter(this)

        //얻댑터와 실제 객체 연결
        viewBinding.vpSecond.adapter = secondVPAdapter

        //tab 제목에 대한 배열
        val tabTitleArray = arrayOf(
            "One", "Two",
        )

        //클래스에 tab 간편 연결 (tab객체, VP객체)                          //함수 실행 시 자동으로 받는 값
        TabLayoutMediator(viewBinding.tabSecond, viewBinding.vpSecond) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()  //attach 필수
    }
}