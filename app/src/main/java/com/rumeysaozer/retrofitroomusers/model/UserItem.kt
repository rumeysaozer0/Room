package com.rumeysaozer.retrofitroomusers.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class UserItem(
    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    val phone: String,
    @ColumnInfo(name = "username")
    @SerializedName("username")
    val username: String,
    @ColumnInfo(name = "website")
    @SerializedName("website")
    val website: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}