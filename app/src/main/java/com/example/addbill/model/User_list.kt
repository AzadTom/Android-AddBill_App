package com.example.addbill.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user_list")
data class User_list(

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var userImg:String?=null,
    var username:String?=null,
    var phoneNumber:Long?=null
):Parcelable

