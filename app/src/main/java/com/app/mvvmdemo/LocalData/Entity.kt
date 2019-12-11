package com.app.mvvmdemo.LocalData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Gautam Mittal
 * 9/12/19
 */

@Entity(tableName = "Logindata")
class LoginData(
    @PrimaryKey(autoGenerate = true) var id:Int?=null,

    @ColumnInfo(name = "email") var email: String="",

    @ColumnInfo(name = "password") var password: String=""

): Serializable
