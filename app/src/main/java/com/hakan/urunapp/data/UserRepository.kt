package com.hakan.urunapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun readAllData(): List<User> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) =userDao.deleteUser(user)




}