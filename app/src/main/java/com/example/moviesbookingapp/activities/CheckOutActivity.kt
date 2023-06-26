package com.example.moviesbookingapp.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.FoodAndBeverageAdapter
import com.example.moviesbookingapp.adapters.FoodAndBeverageInsideAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CheckOutBodySnack
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.dialog_ticket_cancelion_policy.*

class CheckOutActivity : AppCompatActivity() {

    private var mMovieModel: MovieModel = MovieModelImpl

    lateinit var mFoodAndBeverageAdapter: FoodAndBeverageAdapter
    lateinit var mFoodAndBeverageInsideAdapter: FoodAndBeverageInsideAdapter
    private var mSelectedFoods: ArrayList<String> = mutableListOf<String>() as ArrayList<String>
    private var keyValues = ArrayList<Pair<String, String>>()

    private var mSelectedFoodPrice: ArrayList<String> = mutableListOf<String>() as ArrayList<String>
    private var mSeatNames: MutableList<String> = mutableListOf()
    private var mPricePerTicket = 2

    private var mCheckOutBodySnack: MutableList<CheckOutBodySnack> = mutableListOf()

    private lateinit var mTimeSlot: String
    private lateinit var mBookingDate: String

    private lateinit var mMovieID: String
    private lateinit var mCinemaName: String
    private lateinit var mCinemaStartTime: String

    companion object {
        const val IE_TO_CHECKOUT_CANCEL = "IE_TO_CHECKOUT"
        const val IE_LIST_FOOD = "IE_LIST_FOOD"
        const val IE_LIST_PRICE = "IE_LIST_PRICE"
        const val IE_SEAT_NAMES = "IE_SEAT_NAMES"
        const val IE_TIME_FOR_CHECKOUT = "IE_TIME_FOR_CHECKOUT"
        const val IE_BOOKING_DATE = "IE_NAME_FOR_CHECKOUT"

        const val IE_MOVIE_ID_FOR_CALL = "IE_MOVIE_ID_FOR_CALL"
        const val IE_LIST_FOR_POST = "IE_LIST_FOR_POST"
        const val IE_CINEMA_NAME = "IE_CINEMA_NAME"
        const val IE_CINEMA_START_TIME = "IE_CINEMA_START_TIME"

        fun newIntent(
            context: Context,
            isAbleToCancel: Boolean,
            selectedFoodName: ArrayList<String>,
            selectedFoodPrice: ArrayList<String>,
            selectedSeatNameList: MutableList<String>,
            mTime: String,
            bookingDate: String,
            mMovieID: String,
            mCinemaName: String,
            mCinemaStartTime: String,
            updateItem: List<CheckOutBodySnack>
        ): Intent {
//            return Intent(context, CheckOutActivity::class.java).putExtra(IE_TO_CHECKOUT_CANCEL,isAbleToCancel)
            val intent = Intent(context, CheckOutActivity::class.java)
            intent.putStringArrayListExtra(IE_LIST_FOOD, selectedFoodName)
            intent.putStringArrayListExtra(IE_LIST_PRICE, selectedFoodPrice)
            intent.putExtra(IE_TO_CHECKOUT_CANCEL, isAbleToCancel)
            intent.putStringArrayListExtra(
                IE_SEAT_NAMES,
                selectedSeatNameList as java.util.ArrayList<String>
            )
            intent.putExtra(IE_MOVIE_ID_FOR_CALL, mMovieID)

            intent.putExtra(IE_TIME_FOR_CHECKOUT, mTime)
            intent.putExtra(IE_BOOKING_DATE, bookingDate)
            intent.putExtra(IE_LIST_FOR_POST, updateItem.toTypedArray())
            intent.putExtra(IE_CINEMA_START_TIME, mCinemaStartTime)
            intent.putExtra(IE_CINEMA_NAME, mCinemaName)
            return intent
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        mSelectedFoods = intent.getStringArrayListExtra(IE_LIST_FOOD) as ArrayList<String>
        mSelectedFoodPrice = intent.getStringArrayListExtra(IE_LIST_PRICE) as ArrayList<String>
        mSeatNames = intent.getStringArrayListExtra(IE_SEAT_NAMES) as ArrayList<String>

        mBookingDate = intent.getStringExtra(IE_BOOKING_DATE).toString()
        mTimeSlot = intent.getStringExtra(IE_TIME_FOR_CHECKOUT).toString()

        mMovieID = intent.getStringExtra(IE_MOVIE_ID_FOR_CALL).toString()


        mCinemaName = intent.getStringExtra(IE_CINEMA_NAME).toString()
        mCinemaStartTime = intent.getStringExtra(IE_CINEMA_START_TIME).toString()

        val flag = intent.getBooleanExtra(IE_TO_CHECKOUT_CANCEL, false)

        if (flag) {
            ivBtnContinue.visibility = View.GONE
            tvTicketCancelionPolicy.setBackgroundDrawable(getDrawable(R.drawable.background_cancelion_policy))
            tvTitleToolbar.text = getString(R.string.lbl_ticket_details)


            btnCancelBooking.setOnClickListener {
                mMovieModel.deleteTickets(mTimeSlot.toInt())
                finish()
            }

        } else {
            tvRefund.visibility = View.GONE
            tvRefundAmount.visibility = View.GONE
            btnCancelBooking.visibility = View.GONE
            val checkoutSnack =
                intent.getSerializableExtra(IE_LIST_FOR_POST) as Array<CheckOutBodySnack>
            mCheckOutBodySnack = checkoutSnack.toList() as MutableList<CheckOutBodySnack>

            setUpBookingSuccess()
        }



        updatePrice()

        getSnackAndPriceList()
//        setUpUiForCancel()
        setUpcheckoutRecyclerview(mSelectedFoods)
        calculateToalPrice()
        setUpCancelionPolicy()

        setUpListeners()
    }


    @SuppressLint("SetTextI18n")
    private fun calculateToalPrice() {
        var foodTotalPrice: MutableList<Int> = mutableListOf()
        val seatTotalPrice = mSeatNames.count() * mPricePerTicket
        val pricePerItem = keyValues.forEach {
            foodTotalPrice.add(it.second.toInt())
        }
        val fee = tvConvenienceFee.text.toString()
        val intFee = fee.filter { it.isDigit() }.toInt()
        val totalAmount = (seatTotalPrice + foodTotalPrice.sum() + intFee)
        tvTotalCheckOut.text = "${seatTotalPrice + foodTotalPrice.sum() + intFee} ks"
        tvRefundAmount.text = "${totalAmount*0.75} Ks"
    }

    private fun updatePrice() {
        tvCountTickets.text = mSeatNames.count().toString()

        tvSeatNameFromCheckout.text = mSeatNames.joinToString(", ")

        tvSeatSelectedSum.text = "${mSeatNames.count() * mPricePerTicket} ks"

        tvOriginalTitle.text = mMovieModel.getMoviefromDatabase(mMovieID)?.originalTitle
        tvTime.text = mCinemaStartTime
        tvCinemaName.text = mCinemaName
    }

    private fun getSnackAndPriceList() {
        mSelectedFoods.zip(mSelectedFoodPrice).forEach { pair ->
            val key = pair.first
            val value = pair.second
            keyValues.add(Pair(key, value))

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpListeners() {
        val isAbletoCancel = intent.getBooleanExtra(IE_TO_CHECKOUT_CANCEL, false)
        when (isAbletoCancel) {
            false -> {

            }
            true -> {

            }

        }
    }

    private fun setUpUiForCancel() {

    }

    private fun setUpBookingSuccess() {
//        val builder = AlertDialog.Builder(this,R.style.myFullscreenAlertDialogStyle)
//        val inflater = this.layoutInflater
//        builder.setView(inflater.inflate(R.layout.booking_success,null))
//        builder.setCancelable(false)
//        val dialog=builder.create()
        ivBtnContinue.setOnClickListener {

//            dialog.show()
            startActivity(
                PaymentActivity.newIntent(
                    this,
                    mMovieID,
                    mSeatNames, mCheckOutBodySnack, mBookingDate, mTimeSlot
                )
            )
            this.finish()
//            Handler().postDelayed(Runnable {
//                dialog.dismiss()
//
//            },3000)


        }

    }

    private fun setUpCancelionPolicy() {
        tvTicketCancelionPolicy.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_ticket_cancelion_policy)
            dialog.btnCloseDialog.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun setUpcheckoutRecyclerview(mSelectedFoodList: ArrayList<String>) {
        mFoodAndBeverageInsideAdapter = FoodAndBeverageInsideAdapter()
        mFoodAndBeverageAdapter = FoodAndBeverageAdapter(mFoodAndBeverageInsideAdapter, this)
        mFoodAndBeverageInsideAdapter.bindData(keyValues)
        mFoodAndBeverageAdapter.bindData(keyValues)
        rvCheckoutOutter.adapter = mFoodAndBeverageAdapter
        rvCheckoutOutter.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}