package com.example.a3rdclass_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a3rdclass_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //클릭 이벤트 박생시 실행
        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            intent.putExtra("text", "first")
            startActivity(intent)
        }

    }
}

