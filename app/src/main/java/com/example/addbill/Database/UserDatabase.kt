package com.example.addbill.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.addbill.model.Info
import com.example.addbill.model.UserDao
import com.example.addbill.model.User_list
import com.example.addbill.model.InfoDao


@Database(entities = [User_list::class,Info::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {

    abstract fun UserDao():UserDao
    abstract fun infoDao(): InfoDao


}