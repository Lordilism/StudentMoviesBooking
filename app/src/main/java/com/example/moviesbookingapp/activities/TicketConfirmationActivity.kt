package com.example.moviesbookingapp.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CheckOutBody
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.data.vos.TicketCheckOutVO
import com.example.moviesbookingapp.data.vos.TicketForDatabase
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_ticket_confirmation.*

class TicketConfirmationActivity : AppCompatActivity() {
    private lateinit var mMovieID: String
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mCheckOutVo: CheckOutBody
    private lateinit var mTicketCheckOutVO: TicketCheckOutVO

    private var mMovieVo: MovieVO? = null

    companion object {
        const val IE_ID_FOR_MOVIE = "IE_ID_FOR_MOVIE"
        const val IE_CHECKOUT = "IE_LIST_SELECTED_SEAT"

        fun newIntent(context: Context, mMovieID: String, mSeatSelectedList: CheckOutBody): Intent {
            val intent = Intent(context, TicketConfirmationActivity::class.java)
            intent.putExtra(IE_CHECKOUT, mSeatSelectedList)
            intent.putExtra(IE_ID_FOR_MOVIE, mMovieID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confirmation)
        mMovieID = intent.getStringExtra(IE_ID_FOR_MOVIE).toString()
        mCheckOutVo = intent.getSerializableExtra(IE_CHECKOUT) as CheckOutBody

        setupSuccessBooking()

        setUpTicket()
        mMovieID.let {
            requestData(it)
        }

        setUpDoneBtn()

    }

    private fun setUpTicket() {

//        tvCountTickets.text = mTicketCheckOutVO.totalSeat.toString()
//        tvSelectionSeat.text = mTicketCheckOutVO.seat
    }

    private fun setUpDoneBtn() {

        btnDone.setOnClickListener {
            mMovieModel.insertTicket(
                TicketForDatabase(
                    mTicketCheckOutVO,
                    "1871,London",
                    mMovieVo?.originalTitle,
                    mMovieVo?.posterPath
                )
            )
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }


    }

    private fun requestData(id: String) {


        mMovieModel.getMoviesDetails(

            onSuccess = {
                mMovieVo = it
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

            mMovieModel.getTicketCheckout("Bearer ${mMovieModel.getOtp(201)?.token}",
                mCheckOutVo,
                onSuccess = {
                    Log.d("Fail", it.toString())
                    mTicketCheckOutVO = it
                    tvSelectionSeat.text = mTicketCheckOutVO.seat

                    tvCountTickets.text = mTicketCheckOutVO.totalSeat.toString()

                },
                onFailure = {

                })

            dialog.dismiss()
        }, 3000)
    }
}