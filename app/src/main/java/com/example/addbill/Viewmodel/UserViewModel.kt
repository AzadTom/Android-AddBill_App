package com.example.addbill.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addbill.Database.UserDatabase
import com.example.addbill.model.Info
import com.example.addbill.model.User_list
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repositery: UserRepositery):ViewModel() {

    //GetAlluser

    fun getAllUser() = repositery.getAlluser()


      fun billdataByuser(id: String) :LiveData<List<Info>>
      {
          return repositery.getAllData(id)
      }

     fun insertuserBill(info: Info) = viewModelScope.launch(Dispatchers.IO) {
         repositery.insertUserbill(info)
     }




      //getUserNmae
    fun getNameList(name:String) :LiveData<List<User_list>>
      {
        return repositery.getUserByname(name)
    }

    //InsertUser

    fun insertUser(userList: User_list) = viewModelScope.launch(Dispatchers.IO) {

        repositery.insertuser(userList)

    }

    fun updateBill(info: Info) = viewModelScope.launch(Dispatchers.IO) {

        repositery.updateBill(info)

    }

    //Deleteuser

    fun deleteuser(userList: User_list) =viewModelScope.launch(Dispatchers.IO) {
        repositery.deleteUser(userList)
    }

    fun deleteuserbill(info: Info) =viewModelScope.launch(Dispatchers.IO) {

        repositery.deleteuserbill(info)
    }


    fun delteuserByname(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repositery.delteUserByname(name)

    }

    //updateUser
    fun updateUser(userList: User_list)  =viewModelScope.launch(Dispatchers.IO) {
        repositery.updateUser(userList)
    }







}