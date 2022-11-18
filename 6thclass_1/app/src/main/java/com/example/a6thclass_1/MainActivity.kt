package com.example.a6thclass_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a6thclass_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        //첫시작화면을 Home Fragment로 지정
        supportFragmentManager
            .beginTransaction()
            //add를 하면 view끼리 겹쳐서 나오므로 replace 사용 추천
            .replace(viewBinding.containerFragment.id, HomeFragment())  //id반환
            .commitAllowingStateLoss()  //최종적으로 커밋

        //범위 함수. viewBinding.navBottom 다시입력할 필요 X
        viewBinding.navBottom.run{
            setOnItemSelectedListener {
                when (it.itemId) {      //item의 ID로 구분하겠다는 뜻
                    R.id.menu_home -> {     //menu_home 버튼 눌렸을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add를 하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, HomeFragment())  //id반환
                            .commitAllowingStateLoss()  //최종적으로 커밋
                    }
                    R.id.menu_setting -> {  //menu_setting 버튼 눌렸을 때
                        supportFragmentManager
                            .beginTransaction()
                            //add를 하면 view끼리 겹쳐서 나오므로 replace 사용 추천
                            .replace(viewBinding.containerFragment.id, SettingFragment())  //id반환
                            .commitAllowingStateLoss()  //최종적으로 커밋
                    }
                }
                //return값을 true와 false로 받는다.
                true
            }
            //초기에 설정한 것을 알려주기
            selectedItemId = R.id.menu_home //처음 실행 시 menu_home을 가리킨다는 의미
        }
    }
}