package com.app.mvvmdemo.LocalData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @author Gautam Mittal
 * 9/12/19
 */
@Dao
interface LoginDao {
    @Query("SELECT * from Logindata")
    fun getAll(): List<LoginData>


    @Query("SELECT * FROM LOGINDATA WHERE email LIKE :title")
    fun findByEmail(title: String): LoginData

    @Insert(onConflict = 1)
    fun insert(loginData: LoginData)
}

