package com.example.a6thclass_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a6thclass_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var itemBinding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //next_btn이 눌리면 secondActivity로 이동
        itemBinding = FragmentHomeBinding.inflate(inflater,container ,false)
        return itemBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemBinding.nextBtn.setOnClickListener{
            val intent = Intent(this.context,SecondActivity::class.java)
            startActivity(intent)
        }
    }

}