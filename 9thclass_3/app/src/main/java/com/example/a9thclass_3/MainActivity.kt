package com.example.a9thclass_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a9thclass_3.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //Key Hash 값 찾기
        val keyHash = Utility.getKeyHash(this)  //onCreate 안에 입력해주기
        Log.d("Hash", keyHash)

        //kakao developer -> android 플랫폼에 패키지, 해시 값 등록해놓기

        //login 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if(error != null) {
                Toast.makeText(this,"토큰 정보 보기 실패",Toast.LENGTH_SHORT).show()
            }
            else if(tokenInfo != null) {
                Toast.makeText(this,"토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                //이 부분 주석 처리 안하면, 한번 로그인 했으면 로그인 버튼을 클릭하지 않아도 바로 넘어감
//                val intent = Intent(this,KaKaoLoginActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
            }
        }

        //에러 처리
        val callback : (OAuthToken?,Throwable?) -> Unit = { token, error ->
            if(error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key.hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        //Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if(token != null) {
                Toast.makeText(this,"로그인에 성공하였습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, KaKaoLoginActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val kakao_login_button = viewBinding.kakaoLoginBtn
        kakao_login_button.setOnClickListener {
            //카카오톡이 설치되어 있으면
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                //카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                //아니면 카카오 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}