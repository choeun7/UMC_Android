package com.example.a9thclass_3

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a9thclass_3.databinding.ActivityKakaoLoginBinding
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User

class KaKaoLoginActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityKakaoLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val nickname = viewBinding.nickname
        val email = viewBinding.email

        UserApiClient.instance.me{user, error ->
            nickname.text = "닉네임 : ${user?.kakaoAccount?.profile?.nickname}"
            email.text = "이메일 ${user?.kakaoAccount?.email}"
        }
    }
}