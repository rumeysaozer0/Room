package com.rumeysaozer.retrofitroomusers.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rumeysaozer.retrofitroomusers.model.UserItem
import com.rumeysaozer.retrofitroomusers.service.UserDatabase
import kotlinx.coroutines.launch

class UserDetailViewModel(application: Application) : BaseViewModel(application) {
   val userLiveData = MutableLiveData<UserItem>()
    fun getDataFromRoom(uuid: Int){
launch {
    val dao = UserDatabase(getApplication()).userDao()
    val user = dao.getUser(uuid)
    userLiveData.value = user
}

    }
}