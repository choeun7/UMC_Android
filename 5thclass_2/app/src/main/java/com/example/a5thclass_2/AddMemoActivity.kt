package com.example.a5thclass_2

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a5thclass_2.databinding.ActivityAddMemoBinding

class AddMemoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddMemoBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //저장 버튼을 눌렀을 때
        viewBinding.saveMemoBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            //제목, 내용이 비어있지 않다면 전송
            if(viewBinding.editTitle.text.toString()!="" && viewBinding.editContent.text.toString()!="") {
                intent.putExtra("title", viewBinding.editTitle.text.toString())
                intent.putExtra("content", viewBinding.editContent.text.toString())

                setResult(RESULT_OK, intent)
                finish()
            }
            else {
                val builder = AlertDialog.Builder(this)
                    .setTitle("알림")
                    .setTitle("제목과 내용 모두 입력해주세요\n")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, which ->
                            //확인 버튼 누르면 다시 입력창으로
                        })


                builder.show()
            }
        }
    }
}