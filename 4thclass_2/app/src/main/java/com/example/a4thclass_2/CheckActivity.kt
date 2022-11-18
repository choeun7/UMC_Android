package com.example.a4thclass_2


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a4thclass_2.databinding.ActivityCheckBinding

class CheckActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var extras = intent.extras
        var data = extras!!["text"] as String

        viewBinding.receivedMemo.text = data
        }
}