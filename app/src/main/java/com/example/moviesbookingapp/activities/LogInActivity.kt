package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    private val mMovieModel: MovieModel = MovieModelImpl
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,LogInActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        navigateToOTP()


    }

    private fun navigateToOTP() {
        btnVerifyYourPhoneNumber.setOnClickListener {

            mMovieModel.getOtp(

                onSuccess = {
//                    Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()

                    startActivity(GetOTPActivity.newIntent(this,edtPhoneNumber.text.toString()))
                },
                onFailure = {
                    Toast.makeText(this,it,Toast.LENGTH_SHORT).show()

                },
                phoneNo = edtPhoneNumber.text.toString()
            )


        }

    }
}