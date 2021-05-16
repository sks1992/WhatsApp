package com.skkotlin.whatsappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
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
        setSpannableString()

    }
    //create spannable string
    private fun setSpannableString() {
        val span = SpannableString(getString(R.string.waiting_text, phoneNumber))
        val clickSpan: ClickableSpan = object : ClickableSpan() {
            //setting for spannable string
            override fun updateDrawState(ds: TextPaint) {
                ds.color = ds.linkColor // you can use custom color
                ds.isUnderlineText = false // this remove the underline
            }

            //This method load when we click spannable string
            override fun onClick(textView: View) { // handle click event
                showLoginActivity()
            }
        }
        //set the span mean clickable area
        span.setSpan(clickSpan, span.length - 13, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvWaiting.movementMethod = LinkMovementMethod.getInstance()
        tvWaiting.text = span
    }

    private fun showLoginActivity() {
        startActivity(
            Intent(this, LoginActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}