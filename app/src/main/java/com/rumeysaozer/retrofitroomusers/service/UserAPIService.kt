package com.rumeysaozer.retrofitroomusers.service

import com.rumeysaozer.retrofitroomusers.model.UserItem
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserAPIService {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UserAPI::class.java)
    fun getUsers(): Single<List<UserItem>> {
        return api.getUsers()
    }
}