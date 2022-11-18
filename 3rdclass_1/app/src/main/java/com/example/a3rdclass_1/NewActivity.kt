package com.example.a3rdclass_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a3rdclass_1.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //layoutInflator -> XML 파일을 읽어서 객체로 만들어주는 역할
        viewBinding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras
        val data = extras!!["text"] as String

        viewBinding.newtext.text = data
    }

}