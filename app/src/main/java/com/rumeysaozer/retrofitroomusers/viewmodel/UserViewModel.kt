package com.rumeysaozer.retrofitroomusers.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.rumeysaozer.retrofitroomusers.model.UserItem
import com.rumeysaozer.retrofitroomusers.service.UserAPIService
import com.rumeysaozer.retrofitroomusers.service.UserDatabase
import com.rumeysaozer.retrofitroomusers.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : BaseViewModel(application){
    private val userAPIService = UserAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refrehTime = 10*60*1000*1000*1000L
    val users = MutableLiveData<List<UserItem>>()
    fun refrefData(){
        val updateTime = customSharedPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime <refrehTime){
            getDataFromSqlite()
        }else{
            getUsers()
        }

    }
    fun getDataFromSqlite(){
      launch {
          val users = UserDatabase(getApplication()).userDao().getAllUsers()
          showUsers(users)
          Toast.makeText(getApplication(),"Room",Toast.LENGTH_LONG).show()
      }
    }
    fun getUsers(){
        disposable.add(
            userAPIService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<UserItem>>(){
                    override fun onSuccess(t: List<UserItem>) {
                        storeInSqLite(t)
                    }

                    override fun onError(e: Throwable) {
                 e.printStackTrace()
                    }

                })
        )
    }
    private fun showUsers(userList: List<UserItem>){
        users.value = userList
    }
    private fun storeInSqLite(list: List<UserItem>){
        launch {
            val dao = UserDatabase(getApplication()).userDao()
            dao.deleteAllUsers()
          val listLong =   dao.insertAll(*list.toTypedArray())//listeyi tekli hale getiriyor
            var i = 0
            while(i< list.size){
                list[i].uuid = listLong[i].toInt()
                i = i+1
            }
            showUsers(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}