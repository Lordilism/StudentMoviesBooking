package com.example.moviesbookingapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*
import java.util.ArrayList

class TicketConfirmationActivity : AppCompatActivity() {
    private lateinit var mMovieID: String
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mSelectionSeatList: MutableList<String>

    companion object {
        const val IE_ID_FOR_MOVIE = "IE_ID_FOR_MOVIE"
        const val IE_LIST_SELECTED_SEAT = "IE_LIST_SELECTED_SEAT"

        fun newIntent(context: Context, mMovieID: String, mSeatSelectedList: MutableList<String>): Intent {
            val intent = Intent(context,TicketConfirmationActivity::class.java)
            intent.putStringArrayListExtra(IE_LIST_SELECTED_SEAT,mSeatSelectedList as ArrayList<String>)
            intent.putExtra(IE_ID_FOR_MOVIE,mMovieID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confirmation)
        mMovieID = intent.getStringExtra(IE_ID_FOR_MOVIE).toString()
        mSelectionSeatList = intent.getStringArrayListExtra(IE_LIST_SELECTED_SEAT)!!

        setupSuccessBooking()
        setUpTicket()
        mMovieID.let {
            requestData(it)
        }

        setUpDoneBtn()

    }

    private fun setUpTicket() {
        tvCountTickets.text = mSelectionSeatList.count().toString()
        tvSelectionSeat.text = mSelectionSeatList.joinToString(", ")
    }

    private fun setUpDoneBtn() {

        btnDone.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }


    }

    private fun requestData(id: String) {

        mMovieModel.getMoviesDetails(
            onSuccess = {
                Glide.with(this)
                    .load("$IMAGE_BASE_URL${it.posterPath}")
                    .into(ivLogoMovies)
                tvMovieNameFromConfirm.text = it.originalTitle
            },
            onFailure = {

            },
            id
        )

    }


    private fun setupSuccessBooking() {
        val builder = AlertDialog.Builder(this, R.style.myFullscreenAlertDialogStyle)
        val inflater = this.layoutInflater
        builder.setView(inflater.inflate(R.layout.booking_success, null, true))
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()
        Handler().postDelayed(Runnable {
            dialog.dismiss()
        }, 3000)
    }
}