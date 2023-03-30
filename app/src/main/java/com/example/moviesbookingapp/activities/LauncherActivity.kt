package com.example.moviesbookingapp.activities

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.google.android.material.snackbar.Snackbar


class LauncherActivity : AppCompatActivity() {
    private val mMovieModel: MovieModel = MovieModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        navigateToLogin()
        NetworkCallForConfigAndCities()
    }

    private fun NetworkCallForConfigAndCities() {
        mMovieModel.insertCities(
            onSuccess = {
                successCall("Success City")
            }, onFailure = {
              showError(it)
            }
        )

        mMovieModel.insertConfig(
            onSuccess = {
                successCall("Success Config")
            },
            onFailure = {
                showError(it)
            }
        )
    }

    private fun successCall(message: String) {
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        Handler().postDelayed(Runnable {

            if (mMovieModel.getOtp(201)?.token?.isEmpty() == true){
                showError("Null token")
            }else{
                startActivity(LogInActivity.newIntent(this))
            }

            finish()
        }, 3000)

    }

    private fun showError(message:String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }
}