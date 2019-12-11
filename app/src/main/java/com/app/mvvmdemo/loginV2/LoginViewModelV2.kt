package com.app.mvvmdemo.loginV2

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mvvmdemo.LocalData.DataBaseClass
import com.app.mvvmdemo.LocalData.LoginData
import com.app.mvvmdemo.loginV2.MyApplication.Companion.db


/**
 * @author Gautam Mittal
 * 6/12/19
 */
class LoginViewModelV2(val appDataBase:DataBaseClass) : ViewModel() {


    var EmailAddress = MutableLiveData<String>()
    var Password = MutableLiveData<String>()
    var emailLable = MutableLiveData<String>()
    var passwordLable = MutableLiveData<String>()
    var btnText = MutableLiveData<String>()
    var errorHandler = MutableLiveData<ErrorHandler>()
  /*  var errorPasser = MutableLiveData<String>()*/



    init {
        EmailAddress.value = ""
        Password.value = ""
        emailLable.value = "---"
        passwordLable.value = "---"
        btnText.value = "Sign Up"
    }

    private var userMutableLiveData: MutableLiveData<LoginUser>? = null
    val user: MutableLiveData<LoginUser>
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData()
            }
            return userMutableLiveData!!
        }

    fun onClick(view: View?) {

        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        val loginUser = LoginUser(EmailAddress.value!!, Password.value!!, btnText.value!!)
      //  userMutableLiveData!!.setValue(loginUser)
        isDataValid(loginUser)
    }



    /*
    **
    *  This is to make observer to check wheter textfield values entered are valid or not
    *  Same is used by LoginActivityV2 to perform the above specified task
    *
    *
     */


    private fun isDataValid(loginUser: LoginUser): Boolean {
        val isValid=false
        if (TextUtils.isEmpty((loginUser).getStrEmailAddress()))
        {
            errorHandler.value= ErrorHandler("Enter an E-Mail Address", 0)
        }

        else if (!loginUser.isEmailValid())
        {
            errorHandler.value= ErrorHandler("Enter a valid E-Mail Address", 0)
        }

        else if (TextUtils.isEmpty((loginUser).getStrPassword()))
        {
            errorHandler.value= ErrorHandler("Enter a password", 1)
        }
        else if (!loginUser.isPasswordLengthGreaterThan5())
        {
            errorHandler.value= ErrorHandler("Enter a valid password", 1)
        }
        else {


            EmailAddress.value = ""
            Password.value = ""
            if (btnText.value == "LOGIN") {
                val getemail = loginUser.getStrEmailAddress().trim()
                val getpassword = loginUser.getStrPassword().trim()
                val data = db.Dao().getAll()
                val password = data.map { it.password }
                val email = data.map { it.email }
                if (password.contains(getpassword) && email!!.contains(getemail)) {
                    emailLable.value = getemail
                    passwordLable.value = getpassword + " Login Sucessfull"
                    btnText.value = "SignUp"
                } else {
                    emailLable.value = "Opps wrong email or password"
                }
            }

            else {
                val getemail = loginUser.getStrEmailAddress().trim()
                val data = db.Dao().getAll()
                val email = data.map { it.email }
                if (email!!.contains(getemail)) {
                    emailLable.value = getemail
                    passwordLable.value = "Email Already exist"
                } else {
                    val data = LoginData()
                    data.email = loginUser.getStrEmailAddress()
                    data.password = loginUser.getStrPassword()
                    btnText.value = "LOGIN"
                    Thread {
                        data.email = data.email
                        data.password = data.password
                        db.Dao().insert(data)
                    }.start()
                }
            }


            /*

            emailLable.value=loginUser.getStrEmailAddress()
            passwordLable.value=loginUser.getStrPassword()
            btnText.value = loginUser.getBtnText()

            if(btnText.value.equals("SIGN UP"))
            {

            }
            else
            {

            }
        }


            */
        }
        return isValid



    }



}