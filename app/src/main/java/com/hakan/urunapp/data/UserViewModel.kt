package com.hakan.urunapp.data

import  android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.nio.file.Files.delete

class UserViewModel(context: Context) : ViewModel() {

    private var _allUserData: MutableLiveData<List<User>> = MutableLiveData()
    val allUserData: LiveData<List<User>> get() = _allUserData

    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(context).userDao()
        repository = UserRepository(userDao)
    }

    fun readAllData() {
        viewModelScope.launch {
            _allUserData.value = repository.readAllData()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
    // ikinci adım
    fun deleteUser(user: User)=viewModelScope.launch {
        repository.deleteUser(user)
    }
}