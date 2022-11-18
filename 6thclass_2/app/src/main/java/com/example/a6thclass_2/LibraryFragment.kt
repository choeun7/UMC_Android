package com.example.a6thclass_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a6thclass_2.databinding.FragmentLibraryBinding
import com.google.android.material.tabs.TabLayoutMediator

class LibraryFragment : Fragment() {

    lateinit var viewBinding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentLibraryBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val libraryVPAdapter = LibraryVPAdapter(this)
        val vp = viewBinding.vpLibrary

        //어댑터와 실제 객체 연결
        vp.adapter = libraryVPAdapter

        //tab 제목에 대한 배열
        val tabTitleArray = arrayOf(
            "전체","국내","해외","좋아요",
        )

        //클래스에 tab 간편 연결
        TabLayoutMediator(viewBinding.tabLibrary, viewBinding.vpLibrary) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}