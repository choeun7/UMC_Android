package com.example.a6thclass_3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a6thclass_3.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {
    private lateinit var viewBinding : FragmentRadioBinding
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRadioBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val radioAVAdapter = this.activity?.let{RadioAVAdapter(getImageList())}

        //viewBinding레이아웃 선택해서 adapter 연결
        viewBinding.imageSliderVp.apply {
            adapter = radioAVAdapter
//            currentItem = 0
        }
        Log.d("아이템개수",viewBinding.imageSliderVp.adapter?.itemCount.toString())

        //넘어가는 사진의 순서를 보여주는 circleIndicator
        viewBinding.imageSliderIndicator.apply {
            setViewPager(viewBinding.imageSliderVp)     //같은 화면의 viewPager에 연결
            createIndicators(3, 0)    //총 갯수, 처음 시작 순서
        }
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    //drawable에 있는 image들을 arrayList로 만들기
    private fun getImageList() : ArrayList<Int> {
        return arrayListOf(
            R.drawable.music_radio1,
            R.drawable.music_radio2,
            R.drawable.music_radio3
        )
    }

    //화면이 2일때는 0으로 초기화, 아닐 떄 +1
    private fun setPage() {
        if(viewBinding.imageSliderVp.currentItem == 2)
            viewBinding.imageSliderVp.currentItem = 0
        else
            viewBinding.imageSliderVp.currentItem++
        //viewPager에 들어가는 item순서 정의
        viewBinding.imageSliderVp.setCurrentItem(viewBinding.imageSliderVp.currentItem, true)
    }

    inner class PagerRunnable:Runnable {
        override fun run() {
            while (true) {
                try {
                    Thread.sleep(3000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException) {
                    Log.d("interrupt","interrupt 발생")
                }
            }
        }
    }
}