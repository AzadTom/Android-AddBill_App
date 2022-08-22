package com.example.addbill.Viewmodel

import androidx.lifecycle.LiveData
import com.example.addbill.model.Info
import com.example.addbill.model.UserDao
import com.example.addbill.model.User_list
import com.example.addbill.model.InfoDao
import javax.inject.Inject



class UserRepositery @Inject constructor(private val dao: UserDao,private val infoDao: InfoDao) {








     fun getAllData(id:String) :LiveData<List<Info>>
    {

        return  infoDao.allData(id)

    }

    fun insertUserbill(info: Info)
    {
        return infoDao.insertData(info)

    }


    suspend fun updateBill(info: Info) = infoDao.UpdateData(info)


    //deleteuserbill

     suspend fun deleteuserbill(info: Info)  = infoDao.DeleteData(info)





    //getAlluser
     fun getAlluser():LiveData<List<User_list>> {

        return dao.getAllUser()
    }

    //getUserName
     fun getUserByname(username:String):LiveData<List<User_list>>{

        return dao.getUserByname(username)
    }

    //Insertuser
    suspend fun insertuser(User_list:User_list)
    {
        return dao.insertuser(userList = User_list)

    }

    //Deleteuser

    suspend fun deleteUser(userList: User_list)
    {
        return dao.deleteuser(userList)
    }

    //UpdateUser

    suspend fun updateUser(userList: User_list)
    {
        return dao.updateuser(userList)
    }

    suspend fun delteUserByname(name:String) = infoDao.deleteUserByname(name)







}