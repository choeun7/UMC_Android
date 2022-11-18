package com.example.a3rdclass_1

import android.os.Bundle
import android.view.AbsSavedState
import androidx.appcompat.app.AppCompatActivity
import com.example.a3rdclass_1.databinding.ActivityFragBinding
import com.example.a3rdclass_1.databinding.ActivityMainBinding

class FragActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFragBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFragBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameFragment.id, FirstFragment())
            .commitAllowingStateLoss()      //오류방지

        viewBinding.btnFragment1.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id, FirstFragment())
                .commitAllowingStateLoss()      //오류방지
        }
        viewBinding.btnFragment2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id, SecondFragment())
                .commitAllowingStateLoss()      //오류방지
        }
    }
}