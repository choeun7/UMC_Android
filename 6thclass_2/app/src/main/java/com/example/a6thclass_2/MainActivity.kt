package com.example.a6thclass_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a6thclass_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        //첫 시작 화면을 Home Fragment로 지정
        supportFragmentManager
            .beginTransaction()
            //add하면 view끼리 겹쳐서 나오므로 replace 사용 추천
            .replace(viewBinding.containerFragment.id, PlayFragment())
            .commitAllowingStateLoss()  //최종 커밋(실행)

        //범위함수. viewBinding.navBottom을 다시 입력할 필요 X
        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_play -> {     //menu_home 버튼 눌렸을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add를 하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, PlayFragment())  //id반환
                            .commitAllowingStateLoss()  //최종적으로 커밋
                    }
                    R.id.menu_radio -> {    //menu_radio 버튼 눌렀을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, RadioFragment())
                            .commitAllowingStateLoss()  //최종 커밋(실행)
                    }
                    R.id.menu_library -> {    //menu_radio 버튼 눌렀을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, LibraryFragment())
                            .commitAllowingStateLoss()  //최종 커밋(실행)
                    }
                    R.id.menu_search -> {    //menu_radio 버튼 눌렀을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, SearchFragment())
                            .commitAllowingStateLoss()  //최종 커밋(실행)
                    }
                }
                true
            }
            selectedItemId = R.id.menu_play //처음 실행 시 playFragment를 기본으로
        }

    }
}