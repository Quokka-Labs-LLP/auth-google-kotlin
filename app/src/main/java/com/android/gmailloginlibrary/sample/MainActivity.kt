package com.android.gmailloginlibrary.sample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.android.gmaillogin.GmailLogin
import com.android.gmaillogin.util.GoogleLoginListener
import com.android.gmailloginlibrary.R


class MainActivity : AppCompatActivity(), GoogleLoginListener{

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                System.err.println(">>>> inside launcher ")
                GmailLogin.requestForUserdata(result.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GmailLogin.initGoogleClient(this,this, getString(R.string.client_id))

        findViewById<Button>(R.id.button).setOnClickListener {
            GmailLogin.signIn(launcher)
        }
    }

    override fun onGmailLoginError(error: String) {
        System.err.println(">>>>>>>  error $error")
    }

    override fun onGetProfileSuccess(map: HashMap<String, String?>) {
        //for we are getting value in the form of hashMap

        System.err.println(">>>>>>>   on getprofile $map")
        System.err.println(">>>>>>>   " + map["id_token"])
        System.err.println(map["name"])
        System.err.println(map["email"])
        System.err.println(map["gmail_id"])
        if (map["image"] != null) { // in some cases there is no user profile photo.
            System.err.println(map["image"])
        }
    }
}