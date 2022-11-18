package com.example.a4thclass_1;

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a4thclass_1.databinding.ActivityCheckBinding

class CheckActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //var extras = intent.extras
        //var data = extras!!["text"] as String
        var memo = intent.getStringExtra("text")
        viewBinding.receivedMemo.text = memo

        //viewBinding.btnNext.setOnClickListener {
        //    val intent = Intent(this, MainActivity::class.java)
        //    intent.putExtra("check", true)
        //    intent.putExtra("content",memo)
//          startActivity(intent) // intent를 통해서 새로운 액티비티를 띄우는 것 /  앱 실행을 하고 첫번째 액티비티 startActivity -> 2번째 액티비티가 나와 -> 첫번째 액티비티
        //    finish()
        //}
    }

    override fun onBackPressed() {
        setResult(RESULT_OK,intent);
        finish()
        super.onBackPressed()
    }
}
