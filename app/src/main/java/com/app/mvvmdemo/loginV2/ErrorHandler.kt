package com.app.mvvmdemo.loginV2

class ErrorHandler(ErrorMessage: String, ErrorField: Int) {

    val errorMessage: String
    val errorField: Int
    init {
        errorMessage = ErrorMessage
        errorField = ErrorField
    }
}