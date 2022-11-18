package com.example.a5thclass_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a5thclass_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList : ArrayList<Data> = arrayListOf()

        dataList.apply {
            add(Data("hello","1"))
            add(Data("hello","2"))
            add(Data("hello","3"))
            add(Data("hello","4"))
            add(Data("hello","5"))
            add(Data("hello","6"))
            add(Data("hello","7"))
            add(Data("hello","8"))
            add(Data("hello","9"))
            add(Data("hello","10"))
            add(Data("hello","11"))
            add(Data("hello","12"))
            add(Data("hello","13"))
            add(Data("hello","14"))
            add(Data("hello","15"))
        }
        val dataRVAdapter = DataRVAdapter(dataList)
        viewBinding.rvData.adapter = dataRVAdapter
        //LayoutManager설정 필수!!
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)    //layout 종류는 원하는것으로

        //데이터를 추가할 땐 adaptor에 알려주어야 함
        Handler(mainLooper).postDelayed({
            dataList.apply {
                add(Data("hello", "16"))
                add(Data("hello", "17"))
                add(Data("hello", "18"))
            }
            //notifyDataSetChanged를 써도 가능하지만, 사용 시 recyclerview의 item 전체를 가져와야 함
            //dataRVAdapter.notifyDataSetChanged()
            //구간이 변경되었기 때문에 notifyItemRangeChanged 사용
            //notify 종류 중 필요한 것 골라서 사용
            dataRVAdapter.notifyItemRangeChanged(15,4)
        }, 1000)    //1초 딜레이 후 추가

    }
}