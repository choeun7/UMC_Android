package com.example.a5thclass_2

import android.content.ClipData.Item
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5thclass_2.databinding.ActivityMainBinding
import com.example.a5thclass_2.databinding.ItemDataBinding
import java.lang.reflect.Member

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding
    private lateinit var getResultText : ActivityResultLauncher<Intent>
    private lateinit var itemBinding: ItemDataBinding
    val dataList : ArrayList<MemoData> = arrayListOf()

    val dataRVAdaptor = DataRVAdaptor(dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.rvData.adapter = dataRVAdaptor
        //LayoutManager 설정
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

        //메모 추가하기 버튼 눌렀을 때 addMemoActivity로 이동
        viewBinding.addMemoBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
            getResultText.launch(intent)
        }

        //역순으로 (생성된 순) 출력
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        manager.stackFromEnd = true

        viewBinding.rvData.layoutManager = manager

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == RESULT_OK) {
                Handler(mainLooper).postDelayed({
                    dataList.apply {
                        val mTitle = result.data?.getStringExtra("title")
                        val mContent = result.data?.getStringExtra("content")
                        add(MemoData(mTitle.toString(), mContent.toString()))
                    }
                    dataRVAdaptor.notifyItemInserted(dataRVAdaptor.itemCount)
                },1000)     //1초 딜레이 후 추가
            }
        }

    }

}