package com.skkotlin.whatsappdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var phoneNumber:String
    private lateinit var countryCode:String
    private lateinit var alertDialogBuilder: MaterialAlertDialogBuilder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //when next button enabled we check EditText length and decide
        etPhoneNumber.addTextChangedListener {
            btnNext.isEnabled =!(it.isNullOrEmpty()||it.length<10)
        }

        //when we click on Next button we need to check either number is right or wrong
        btnNext.setOnClickListener {
            checkedNumber()
        }
    }

    //function is either number is valid or not
    private fun checkedNumber() {

        countryCode = ccPicker.selectedCountryCodeWithPlus
        phoneNumber = countryCode + etPhoneNumber.text.toString()

        if (validatePhoneNumber(etPhoneNumber.text.toString())) {
            notifyUser(
                "We will be verifying the phone number:$phoneNumber\n" +
                        "Is this OK, or would you like to edit the number?"
            )
        } else {
            toast("Please enter a valid number to continue!")
        }
    }

    //validate phone number
    private fun validatePhoneNumber(phone: String): Boolean {
        if (phone.isEmpty()) {
            return false
        }
        return true
    }

    //after we checked phone number we need to inform user about it so we create a dialog
    private fun notifyUser(message: String) {
        alertDialogBuilder = MaterialAlertDialogBuilder(this).apply {
            setMessage(message)
            setPositiveButton("Ok") { _, _ ->
                showOtpActivity()
            }

            setNegativeButton("Edit") { dialog, _ ->
                dialog.dismiss()
            }

            setCancelable(false)
            create()
            show()
        }
    }

    private fun showOtpActivity() {

    }

}

//for showing Toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}