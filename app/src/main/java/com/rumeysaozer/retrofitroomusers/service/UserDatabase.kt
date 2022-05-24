package com.rumeysaozer.retrofitroomusers.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rumeysaozer.retrofitroomusers.model.User
import com.rumeysaozer.retrofitroomusers.model.UserItem

@Database(entities = arrayOf(UserItem::class),version = 1 )
abstract class UserDatabase : RoomDatabase()  {
    abstract fun userDao(): UserDAO
//Singleton
    companion object{
      @Volatile  private var instance : UserDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,UserDatabase::class.java, "userdatabase"
        ).build()
    }
}