package com.example.a8thclass_1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user : User) //룸디비에서 알아서 SQL에 insertinto를 작성해줌

    @Delete
    fun delete(user : User)

    //select는 return 타입을 정해줘야 함
    @Query("SELECT * FROM User")      //모두 가져오는 것
    fun selectAll() : List<User>    //여러가지를 반환하기 때문에 return type을 리스트로

    @Query("SELECT * FROM User WHERE userId = :userId")      //개별로 가져오는 것
    fun selectByUserId(userId : Int) : User

    @Query("SELECT * FROM User WHERE name = :name")         //이름으로 찾는 것
    fun selectByUserName(name : String) : List<User>        //혹시나 동일 이름이 있을 경우 대비 리스트로 반환 타입 지정

    @Query("UPDATE User SET name = :name WHERE userId = :userId")
    fun updateNameByUserId(userId : Int, name : String)
}