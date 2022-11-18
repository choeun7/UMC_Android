package com.example.a7thclass_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.a7thclass_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //뒤로가기 버튼 누른 시각 저장
    var initTime : Long = 0
    var pauseTime : Long = 0

    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.startBtn.setOnClickListener {
            viewBinding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            viewBinding.chronometer.start()

            //버튼 표시 여부 조정
            viewBinding.stopBtn.isEnabled = true
            viewBinding.resetBtn.isEnabled = true
            viewBinding.startBtn.isEnabled = false
        }

        viewBinding.stopBtn.setOnClickListener {
            pauseTime = viewBinding.chronometer.base - SystemClock.elapsedRealtime()
            viewBinding.chronometer.stop()

            viewBinding.stopBtn.isEnabled = false
            viewBinding.resetBtn.isEnabled = true
            viewBinding.startBtn.isEnabled = true
        }

        viewBinding.resetBtn.setOnClickListener {
            pauseTime = 0
            viewBinding.chronometer.base = SystemClock.elapsedRealtime()
            viewBinding.chronometer.stop()

            viewBinding.stopBtn.isEnabled = false
            viewBinding.resetBtn.isEnabled = false
            viewBinding.startBtn.isEnabled = true
        }
    }

    //뒤로가기 버튼 이벤트 핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //뒤로가기 버튼 눌렀을 때 처리
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 2000) {
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}