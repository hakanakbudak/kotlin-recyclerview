package com.hakan.urunapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Query(value= "SELECT * FROM users ORDER BY name ASC")
    suspend fun readAllData(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    //birinci adım
    @Delete
    suspend fun deleteUser(user: User)
}