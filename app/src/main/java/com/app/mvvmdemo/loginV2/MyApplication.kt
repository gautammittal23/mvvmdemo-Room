package com.app.mvvmdemo.loginV2

import android.app.Application
import com.app.mvvmdemo.LocalData.DataBaseClass

class MyApplication : Application() {
    //var globalVar = "I am Global Variable"

   // var db= DataBaseClass.getInstance(this)

    companion object {
        private  var app: MyApplication?=null
        fun getApp(): MyApplication? {
            return app
        }
        val db: DataBaseClass get() = DataBaseClass.getAppDatabase(app!!.applicationContext)
    }
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}