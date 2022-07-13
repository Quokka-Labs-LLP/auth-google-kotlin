package com.android.gmaillogin.util

interface GoogleLoginListener {
    fun onGmailLoginError(error:String)
    fun onGetProfileSuccess(map: HashMap<String, String?>)
}