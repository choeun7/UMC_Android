package com.example.a8thclass_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a8thclass_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        /*----------sharedPreferences 연습----------*/
        //name 대신 packageName으로 작성 가능, 임의로 name 지정도 가능
        val sharedPrefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        //값을 수정하기 위한 객체 editor
        val editor = sharedPrefs.edit()

        //editor를 이용해 값 넣기 put
        editor.putString("eric","android")  //key값, value값
        //저장할게 더이상 없음을 나타내는 apply
        editor.apply()

        //값을 가져올 땐 get, 뒤에는 디폴트값
        val spvalue = sharedPrefs.getString("eric", "")
        Log.d("SP","${spvalue}")


        /*----------RoomDB 연습----------*/
        val roomDB = AppDatabase.getInstance(this)  //객체를 따로 만들지 않고 getInstance하면 바로 실행가능

        //null check
        if(roomDB != null) {

            //primaryKey는 따로 입력할 필요 X
            val user = User("에릭",23)
            roomDB.userDao().insert(user)       //roomDB에 데이터 들어감

            //추가 update
            //roomDB.userDao().updateNameByUserId(1,"루나")

            //삭제 delete
            //val deleteUser= User("",0,1)
            //roomDB.userDao().delete(deleteUser)

            val userList = roomDB.userDao().selectAll() //roomDB에 저장된 모든 정보 가져오기
            Log.d("DB","User List : ${userList}")
            
        }
    }
}