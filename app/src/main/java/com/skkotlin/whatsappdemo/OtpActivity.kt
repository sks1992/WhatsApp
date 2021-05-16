package com.skkotlin.whatsappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp.*

const val PHONE_NUMBER = "phoneNumber"

class OtpActivity : AppCompatActivity() {

    var phoneNumber:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        otpView()
    }

    //this function get phone number from login activity ,
    // set spnnable string,send sms code from firebase
    private fun otpView() {
        phoneNumber =intent.getStringExtra(PHONE_NUMBER)
        tvVerify.text =getString(R.string.verify_num, phoneNumber)

    }
}