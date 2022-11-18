package com.example.a4thclass_2
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.a4thclass_2.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    private var memo : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //저장 버튼 누르면 실행
        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this,CheckActivity::class.java)
            memo = viewBinding.writeMemo.text.toString()
            intent.putExtra("text", memo)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle","onStop")

        memo = viewBinding.writeMemo.text.toString()
        viewBinding.writeMemo.setText("")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("Lifeycle","onRestart")

            val builder = AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("계속 작성하시겠습니까?")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        viewBinding.writeMemo.setText(memo)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        viewBinding.writeMemo.setText("")
                        memo = ""
                    })
            builder.show()
        }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("LiftCycle","onDistroy")
    }
}