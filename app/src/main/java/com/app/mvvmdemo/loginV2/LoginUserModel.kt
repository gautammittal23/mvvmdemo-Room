package com.app.mvvmdemo.loginV2

import android.util.Patterns
import com.app.mvvmdemo.LocalData.LoginData


/**
 * @author Gautam Mittal
 * 7/12/19
 */


/*
*Bussiness model here
 */



class LoginUser(EmailAddress: String, Password: String, BtnText: String) {



    lateinit var loginData:LoginData

     //lateinit var context:Context


    private val strEmailAddress: String
    private val strPassword: String
    private val btnText: String

   // var db= DataBaseClass.getInstance(context)

    var mApp = MyApplication()
  //  var db = mApp.db


    fun getStrEmailAddress(): String {
        return strEmailAddress
    }

    fun getStrPassword(): String {
        return strPassword
    }

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches()
    }


    fun isPasswordLengthGreaterThan5(): Boolean {
        return getStrPassword().length > 5
    }

    fun isSignIn(): Boolean {
        return btnText == "Login"
    }

    fun getBtnText(): String {

        if(isSignIn())
            return "Sign Up"
        else
            return "Login"
       // return if (isSignIn()) "Sign Up" else "Login"
    }


    fun checkUserAvail(email:String)
    {
      // db.Dao().findByEmail(email)
    }


    fun addUser()
    {

     //  db.Dao().insert(loginData)

    }

    init {
        strEmailAddress = EmailAddress
        strPassword = Password
        btnText = BtnText
    }
}