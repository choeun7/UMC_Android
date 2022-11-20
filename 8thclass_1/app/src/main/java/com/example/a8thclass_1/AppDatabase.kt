package com.example.a8thclass_1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//데이터베이스 클래스는 abstract 필수
@Database(entities = [User::class], version = 1)
//여러 클래스가 들어갈 수 있으니 배열로 넣어주기
//데이터베이스의 구조가 바뀌는 경우 version 으로 구분
//UserDatabase의 구조를 바꾸면 반드시 version도 바꿔야 함
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao    //자동으로 UserDao와 연결

    //전역적으로 사용할 수 있는 변수/함수를 담는
    companion object {
        private  var appDatabase : AppDatabase? = null
        //null허용. 초기화는 함수를 통해 해야하기 때문에

        @Synchronized   //여러 스레드에서 하나의 자원에 접근하는 상황에 내가 사용하고 있다는 의미
        fun getInstance(context : Context): AppDatabase? {
            if(appDatabase == null) {
                synchronized(AppDatabase::class.java) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"  //만드는 룸디비마다 다른 이름
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}