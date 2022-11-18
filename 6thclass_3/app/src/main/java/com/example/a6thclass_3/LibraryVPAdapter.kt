package com.example.a6thclass_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LibraryVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Music2Fragment()
            1 -> Audio2Fragment()
            2 -> Tv2Fragment()
            3 -> Dj2Fragment()
            else -> Music2Fragment()
        }
    }

}