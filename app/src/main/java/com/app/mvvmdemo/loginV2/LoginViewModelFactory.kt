package com.app.mvvmdemo.loginV2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mvvmdemo.LocalData.DataBaseClass

class LoginViewModelFactory(private val appDataBase: DataBaseClass) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModelV2::class.java)) {
            return LoginViewModelV2(appDataBase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}