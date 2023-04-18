package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.PaymentAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.delegates.PaymentDelegate
import kotlinx.android.synthetic.main.activity_payment.*
import java.util.ArrayList

class PaymentActivity : AppCompatActivity(),PaymentDelegate {
    lateinit var mPaymentAdapter: PaymentAdapter
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mMovieID: String
    private lateinit var mSeatSelectedList:MutableList<String>
    companion object{
        const val IE_FOR_MOVIE_ID = "IE_ID_FOR_MOVIE_ID"
        const val IE_SELECTED_SEAT_LIST = "IE_SELECTED_SEAT_LIST"
        fun newIntent(context: Context, mMovieID: String, mSeatNames: MutableList<String>):Intent{
            val intent = Intent(context,PaymentActivity::class.java)
            intent.putExtra(IE_FOR_MOVIE_ID,mMovieID)
            intent.putStringArrayListExtra(IE_SELECTED_SEAT_LIST,mSeatNames as ArrayList<String>)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        mMovieID = intent.getStringExtra(IE_FOR_MOVIE_ID).toString()
        mSeatSelectedList = intent.getStringArrayListExtra(IE_SELECTED_SEAT_LIST)!!
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
        rvPaymentMethod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    override fun onTapPayment() {
        startActivity(TicketConfirmationActivity.newIntent(this,mMovieID,mSeatSelectedList))

    }
}