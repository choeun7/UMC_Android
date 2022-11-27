package com.example.a5thclass_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5thclass_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //val roomDB = AppDatabase.getInstance(this)
    private lateinit var viewBinding : ActivityMainBinding
    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val dataList : ArrayList<MemoData> = arrayListOf()  //dao에서는 arraylist지원 안함
        val dataRVAdaptor = DataRVAdaptor(dataList)
        viewBinding.rvData.adapter = dataRVAdaptor

        //역순으로 (생성된 순) 출력
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        manager.stackFromEnd = true
        //LayoutManager 설정
        viewBinding.rvData.layoutManager = manager

        //아이템 클릭하면 수정하기 위해 AddMemoActivity로 이동
        dataRVAdaptor.setOnItemClickListener(object : DataRVAdaptor.OnItemClickListener {
            override fun onItemClick(view: View, data: MemoData, position: Int) {
                val intent = Intent(this@MainActivity, AddMemoActivity::class.java)
                intent.putExtra("title", data.title)
                intent.putExtra("content", data.content)
                intent.putExtra("memo_type", receive_FIX) //수정일 때 1 전달
                intent.putExtra("position", position)
                getResultText.launch(intent)
            }
        })

        dataRVAdaptor.setOnBtnClickListener(object : DataRVAdaptor.OnBtnClickListener {
            override fun onBtnClick(view: View, data: MemoData, position: Int) {
                dataList.removeAt(position)
                dataRVAdaptor.notifyItemRemoved(position)
            }
        })

        //메모 추가하기 버튼 눌렀을 때 addMemoActivity로 이동
        viewBinding.addMemoBtn.setOnClickListener {
            intent = Intent(this,AddMemoActivity::class.java)
            intent.putExtra("memo_type",receive_SAVE)      //새로 만들 때 0 전달
            getResultText.launch(intent)
        }

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            //추가일 때
            if (result.resultCode == receive_SAVE) {
                Log.d("choeunTest", "Main에서 추가")
                Handler(mainLooper).postDelayed({
                    dataList.apply {
                        val mTitle = result.data?.getStringExtra("title")
                        val mContent = result.data?.getStringExtra("content")
                        var memo = MemoData(mTitle.toString(), mContent.toString())
                        add(memo)
                        Log.d("확인","insert ${mTitle}")
                    }
                    dataRVAdaptor.notifyItemInserted(dataRVAdaptor.itemCount)
                }, 100)     //0.1초 딜레이 후 추가
            }
            //수정일 때
            else if (result.resultCode == receive_FIX) {
                Log.d("choeunTest", "Main에서 수정")
                val test = intent.getStringExtra("memo_type").toString()
                Log.d("choeun", "$test")
                val mTitle = result.data?.getStringExtra("title")
                val mContent = result.data?.getStringExtra("content")
                val mPosition = result.data?.getIntExtra("position", 0)
                dataList.apply {
                    if (mPosition != null) {
                        set(mPosition, MemoData(mTitle.toString(), mContent.toString()))
                        dataRVAdaptor.notifyItemChanged(mPosition)
                    }
                }
            }
        }
    }

    companion object{
        const val receive_SAVE = 1001
        const val receive_FIX = 1002
    }

}