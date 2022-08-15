package com.example.addbill.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Info")
data class Info (


    @PrimaryKey(autoGenerate = true)
    var Id: Int?=null,
    var ItemImg:String,
    var username:String,
    var Itemname:String,
    var Amount:Int,
        ) :Parcelable
