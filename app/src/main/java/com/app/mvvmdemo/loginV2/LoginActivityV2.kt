package com.app.mvvmdemo.loginV2

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.mvvmdemo.R
import com.app.mvvmdemo.databinding.ActivityLoginV2Binding


class LoginActivityV2 : AppCompatActivity() {

    private var mBinding: ActivityLoginV2Binding? = null
    private var mLoginViewModel: LoginViewModelV2? = null
    private var factory: LoginViewModelFactory? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factory = LoginViewModelFactory(MyApplication.db)
        mLoginViewModel = ViewModelProviders.of(this,this.factory).get(LoginViewModelV2::class.java)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_v2);
        mBinding!!.lifecycleOwner = this
        mBinding!!.loginViewModel = mLoginViewModel



        mLoginViewModel!!.errorHandler.observe(this,
            Observer<ErrorHandler> { t ->
                when(t!!.errorField){
                    0-> {

                        mBinding!!.txtEmailAddress.error = t.errorMessage
                        mBinding!!.txtEmailAddress.requestFocus()

                        Toast.makeText(this,t.errorMessage,Toast.LENGTH_LONG).show()
                    }
                    1->{
                        mBinding!!.txtPassword.error = t.errorMessage
                        mBinding!!.txtPassword.requestFocus()

                        Toast.makeText(this,t.errorMessage,Toast.LENGTH_LONG).show()
                    }
                }
            })


 /*       mLoginViewModel!!.user.observe(this, object : Observer<LoginUser?> {
            override fun onChanged(@Nullable loginUser: LoginUser?) {
                if (TextUtils.isEmpty((loginUser)!!.getStrEmailAddress())) {
                    mBinding!!.txtEmailAddress.setError("Enter an E-Mail Address")
                    mBinding!!.txtEmailAddress.requestFocus()
                } else if (!loginUser.isEmailValid()) {
                    mBinding!!.txtEmailAddress.setError("Enter a Valid E-mail Address")
                    mBinding!!.txtEmailAddress.requestFocus()
                } else if (TextUtils.isEmpty((loginUser).getStrPassword())) {
                    mBinding!!.txtPassword.setError("Enter a Password")
                    mBinding!!.txtPassword.requestFocus()
                } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    mBinding!!.txtPassword.setError("Enter at least 6 Digit password")
                    mBinding!!.txtPassword.requestFocus()
                } else
                {
                    mBinding!!.lblPasswordAnswer.setText(loginUser.getStrPassword())
                    mLoginViewModel!!.emailLable.postValue(loginUser.getStrEmailAddress())
                    mLoginViewModel!!.passwordLable.value=loginUser.getStrPassword()
                    if (loginUser.isSignIn()){
                        //Login

                      //  loginUser.checkUserAvail(loginUser.getStrEmailAddress())
                    }else{
                        //signUp
                      //  loginUser.addUser()
                    }
                    mLoginViewModel!!.btnText.value=loginUser.getBtnText()

                }
            }

        })*/
    }
}
