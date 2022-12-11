package com.example.a9thclass_3

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "163c5d0e8f4049644de95183bba01027")
    }
}