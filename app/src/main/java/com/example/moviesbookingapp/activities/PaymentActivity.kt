package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.PaymentAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CheckOutBody
import com.example.moviesbookingapp.data.vos.CheckOutBodySnack
import com.example.moviesbookingapp.delegates.PaymentDelegate
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity(), PaymentDelegate {
    lateinit var mPaymentAdapter: PaymentAdapter
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mMovieID: String
    private lateinit var mSeatSelectedList: MutableList<String>

    private lateinit var mBookingDate: String
    private var mCheckoutSnack: MutableList<CheckOutBodySnack> = mutableListOf()
    private lateinit var mTimeSlotID: String

    private lateinit var mCheckOutBody: CheckOutBody

    companion object {
        const val IE_FOR_MOVIE_ID = "IE_ID_FOR_MOVIE_ID"
        const val IE_SELECTED_SEAT_LIST = "IE_SELECTED_SEAT_LIST"
        const val IE_BOOKING_DATE = "IE_BOOKING_DATE"
        const val IE_TIMESLOT_ID = "IE_TIMESLOT_ID"
        const val IE_FOOD = "IE_FOOD"
        fun newIntent(
            context: Context,
            mMovieID: String,
            mSeatNames: MutableList<String>,
            mSelectedFoods: List<CheckOutBodySnack>,
            mBookingDate: String,
            mTimeSlotID: String
        ): Intent {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(IE_FOR_MOVIE_ID, mMovieID)
            intent.putStringArrayListExtra(IE_SELECTED_SEAT_LIST, mSeatNames as ArrayList<String>)
            intent.putExtra(IE_BOOKING_DATE, mBookingDate)
            intent.putExtra(IE_TIMESLOT_ID, mTimeSlotID)
            intent.putExtra(IE_FOOD, mSelectedFoods.toTypedArray())
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        mMovieID = intent.getStringExtra(IE_FOR_MOVIE_ID).toString()
        mSeatSelectedList = intent.getStringArrayListExtra(IE_SELECTED_SEAT_LIST)!!

        val array = intent.getSerializableExtra(IE_FOOD) as Array<CheckOutBodySnack>?
        mCheckoutSnack = array?.toList() as MutableList<CheckOutBodySnack>

        mBookingDate = intent.getStringExtra(IE_BOOKING_DATE).toString()
        mTimeSlotID = intent.getStringExtra(IE_TIMESLOT_ID).toString()


        setUpPaymentRecyclerView()
        requestData()

    }

    private fun requestData() {
        mMovieModel.getPayment(
            "Bearer ${mMovieModel.getOtp(201)?.token}",
            onSuccess = {
                mPaymentAdapter.setNewData(it)
            },
            onFailure = {

            }

        )
    }

    private fun setUpPaymentRecyclerView() {
        mPaymentAdapter = PaymentAdapter(this)
        rvPaymentMethod.adapter = mPaymentAdapter
        rvPaymentMethod.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapPayment(id: Int?) {
        val str=mSeatSelectedList.joinToString(",")
        mCheckOutBody = CheckOutBody(
            mMovieID.toInt(),
            mBookingDate,
            mTimeSlotID.toInt(),
            str,
            mCheckoutSnack,
            id
        )


        startActivity(
            TicketConfirmationActivity.newIntent(
                this,
                mMovieID,
                mCheckOutBody
            )
        )


    }
}