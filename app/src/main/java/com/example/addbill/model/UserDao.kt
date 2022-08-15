package com.example.addbill.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Query("SELECT * FROM user_list ORDER BY username ASC")
     fun getAllUser():LiveData<List<User_list>>


      @Query("SELECT * FROM user_list WHERE username =:username")
      fun getUserByname(username:String):LiveData<List<User_list>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertuser(userList: User_list)


    @Update
    suspend fun updateuser(userList: User_list)


    @Delete
    suspend fun deleteuser(userList: User_list)








}