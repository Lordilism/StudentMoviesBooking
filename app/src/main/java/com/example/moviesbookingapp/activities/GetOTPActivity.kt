package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_get_otpactivity.*

class GetOTPActivity : AppCompatActivity() {
    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {
        const val IE_PHONE_NO = "Phone"
        fun newIntent(context: Context, phone: String): Intent {

            return Intent(context, GetOTPActivity::class.java).putExtra(IE_PHONE_NO, phone)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otpactivity)
        navigateToPickLocation()

        //for Otp


    }

    private fun navigateToPickLocation() {


        btnConfirmOTP.setOnClickListener {


            val phone = intent.getStringExtra(IE_PHONE_NO).toString()
            val otpbox1 = otp_box_1.text.toString()
            val otpbox2 = otp_box_2.text.toString()
            val otpbox3 = otp_box_3.text.toString()
            val otpbox4 = otp_box_4.text.toString()
            val otpbox5 = otp_box_5.text.toString()
            val otpbox6 = otp_box_6.text.toString()
            val otp = listOf(otpbox1, otpbox2, otpbox3, otpbox4, otpbox5, otpbox6)

            val stringBuilder: StringBuilder = StringBuilder()


            for (lines in otp) {
                stringBuilder.append(lines)
            }
            val EnteredOTP = stringBuilder.toString()
            Log.i(EnteredOTP, "Otp")
            if (EnteredOTP == "123456") {
                mMovieModel.signInOTP(
                    onSuccess = {
                        Toast.makeText(this, it.token, Toast.LENGTH_SHORT).show()

                        startActivity(PickLocationActivity.newIntent(this))
                    },
                    onFailure = {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


                    },
                    phoneNo = phone,
                    otp = EnteredOTP

                )

            }else{
                Toast.makeText(this,"Oops!!",Toast.LENGTH_SHORT).show()
            }


        }
    }
}