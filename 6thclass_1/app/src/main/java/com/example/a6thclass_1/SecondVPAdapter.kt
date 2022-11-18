package com.example.a6thclass_1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SecondVPAdapter(fragmentActivity: SecondActivity) : FragmentStateAdapter(fragmentActivity) {
    //총 아이템의 갯수
    override fun getItemCount(): Int = 2

    //포지션에 따라 어떤 Fragment를 보여줄지 결정
    override fun createFragment(position: Int): Fragment {
       return when (position) {
           0 -> OneFragment()
           1 -> TwoFragment()
           else -> OneFragment()    //둘 다 아닐 때 어떤 Fragment?
       }
    }

}