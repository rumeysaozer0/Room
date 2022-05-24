package com.rumeysaozer.retrofitroomusers.service

import com.rumeysaozer.retrofitroomusers.model.UserItem
import io.reactivex.Single
import retrofit2.http.GET

interface UserAPI {
    @GET("users")
    fun getUsers(): Single<List<UserItem>>

}