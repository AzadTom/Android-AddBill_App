package com.example.addbill.Modules

import android.content.Context
import androidx.room.Room
import com.example.addbill.Database.UserDatabase
import com.example.addbill.Viewmodel.UserRepositery
import com.example.addbill.model.UserDao
import com.example.addbill.model.InfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {



    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context):UserDatabase
    {


        return Room.databaseBuilder(context,UserDatabase::class.java,"user_database").build()


    }

    @Provides
    @Singleton
    fun provideUserDao( userDatabase: UserDatabase) :UserDao
    {
        return userDatabase.UserDao()
    }

    @Provides
    @Singleton
    fun provideinfoDao( userDatabase: UserDatabase) : InfoDao
    {
        return userDatabase.infoDao()
    }




    @Provides
    @Singleton
    fun providesRepo(userDao: UserDao,infoDao: InfoDao):UserRepositery
    {
        return UserRepositery(userDao,infoDao)

    }


}