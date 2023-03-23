package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.PaymentAdapter
import com.example.moviesbookingapp.delegates.PaymentDelegate
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity(),PaymentDelegate {
    lateinit var mPaymentAdapter: PaymentAdapter
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,PaymentActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        setUpPaymentRecyclerView()

    }

    private fun setUpPaymentRecyclerView() {
        mPaymentAdapter = PaymentAdapter(this)
        rvPaymentMethod.adapter = mPaymentAdapter
        rvPaymentMethod.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    override fun onTapPayment() {
        startActivity(TicketConfirmationActivity.newIntent(this))

    }
}