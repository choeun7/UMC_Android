package com.example.a9thclass_1

//response는 name과 type을 동일하게 작성
//AIP에서 Response Parameter를 보고 작성
data class Response(
    val isSuccess : Boolean,
    val code : Int,
    val message : String
)
