package com.hakan.urunapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var marca:String,
    var price:Int ) :Parcelable
