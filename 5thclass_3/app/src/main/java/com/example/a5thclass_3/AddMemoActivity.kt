package com.example.a5thclass_3

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a5thclass_3.databinding.ActivityAddMemoBinding

class AddMemoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddMemoBinding
    private var mTitle : String? = null
    private var mContent : String? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var result_code = 0
        //MainActivity에서 보낸 memo_type가 1, 즉 수정 버튼을 눌렀을 떄
        if(intent.getIntExtra("memo_type",0) == FIX) {

            Log.d("choeuntest","수정")
            mTitle = intent.getStringExtra("title")
            mContent = intent.getStringExtra("content")
            viewBinding.editTitle.setText(mTitle.toString())
            viewBinding.editContent.setText(mContent.toString())
            result_code = FIX//메모 수정일 때 memo_type에 0 전달
        }
        else if(intent.getIntExtra("memo_type",0)==SAVE){
            result_code = SAVE//새로운 메모 작성일 때 memo_type에 1 전달
        }
        //MainActivity에서 보낸 memo_type가 0, 즉 새로운 메모 추가 버튼일 때
        //저장 버튼을 눌렀을 때
        viewBinding.saveMemoBtn.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)

            //제목, 내용이 비어있지 않다면 전송
            if (viewBinding.editTitle.text.toString() != "" && viewBinding.editContent.text.toString() != "") {
                intent2.putExtra("title", viewBinding.editTitle.text.toString())
                intent2.putExtra("content", viewBinding.editContent.text.toString())
                intent2.putExtra("position", intent.getIntExtra("position",0))
                setResult(result_code, intent2)
                finish()
            }
            //제목이나 내용이 비어있다면 알림창 띄우기
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



//            getResultText = registerForActivityResult(
//                ActivityResultContracts.StartActivityForResult()
//            ) { result ->
//                Handler(mainLooper).postDelayed({
//                    dataList.apply {
//                        val mTitle = result.data?.getStringExtra("title")
//                        val mContent = result.data?.getStringExtra("content")
//                    }
//                    dataRVAdaptor.notifyItemInserted(dataRVAdaptor.itemCount)
//                }, 1000)     //1초 딜레이 후 추가
            }
    companion object{
        const val SAVE = 1001
        const val FIX = 1002
    }
}
