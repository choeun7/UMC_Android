package com.example.a8thclass_1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//객체를 담을 객체 생성 클래스
@Entity //룸디비에서 사용하는 객체라는 의미
data class User(
    @ColumnInfo(name = "name")val name : String,    //사용자 이름
    @ColumnInfo(name = "age") val age : Int,        //사용지 나이
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userId") val userId : Int = 0
    //객체의 고유 키, 모든 데이터베이스 안에 1개만 들어갈 수 있음
    //1은 1개, 2는 한개 이런 식
)
