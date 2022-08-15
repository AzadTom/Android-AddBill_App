package com.example.addbill.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface InfoDao {

    @Query("SELECT * FROM Info WHERE username=:name")
    //getData
    fun allData(name: String): LiveData<List<Info>>

    //insert Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(info: Info)




    //Delete data
   @Delete
    suspend fun DeleteData(info: Info)



    @Update
    suspend fun UpdateData(info: Info)

    @Query("DELETE  FROM Info WHERE username=:name")
    suspend fun deleteUserByname(name:String)
}