package com.example.a3rdclass_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a3rdclass_2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    //second_activity에 플러그를 꽂아 java와 activity를 연결하겠다는 뜻
    //vBinding, myBinding, viewBinding 등등 변수 이름은 상관 없음
    //한 자바 파일에 두세개 바인딩도 선언 가능
    private lateinit var viewBinding: ActivitySecondBinding

    //화면이 생성되면 처음 한번 실행할 함수 onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding 변수에 inflate 값 넣기
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //intent로 받은 key가 "text"인 내용은 String type이 확실하고, data에 저장하겠다는 뜻
        val extras = intent.extras
        val data = extras!!["text"] as String

        //second_activity에 연결한 viewBinding의 received_text라는 id를 가진 textview의
        //text를 저장했던 data로 넣는다는 뜻
        viewBinding.receivedText.text = data

        viewBinding.btnNext.setOnClickListener {

            val intent = Intent(this, ThirdActivity::class.java)

            startActivity(intent)
        }
    }
}