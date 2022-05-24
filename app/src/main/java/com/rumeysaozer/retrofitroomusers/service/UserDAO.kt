package com.rumeysaozer.retrofitroomusers.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rumeysaozer.retrofitroomusers.model.UserItem
@Dao
interface UserDAO {

    @Insert
    suspend fun insertAll(vararg users: UserItem):List<Long>

    @Query("SELECT * FROM useritem")
    suspend fun getAllUsers(): List<UserItem>

    @Query("SELECT * FROM useritem WHERE uuid = :userId")
    suspend fun getUser(userId: Int): UserItem

    @Query("DELETE FROM useritem")
    suspend fun deleteAllUsers()
}