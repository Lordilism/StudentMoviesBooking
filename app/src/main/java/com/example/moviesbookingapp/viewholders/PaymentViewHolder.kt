package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.PaymentVO
import com.example.moviesbookingapp.delegates.PaymentDelegate
import kotlinx.android.synthetic.main.viewholder_payment.view.*

class PaymentViewHolder(itemView: View, mDelegate: PaymentDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mPaymentVO:PaymentVO? = null
    fun bindData(paymentVO: PaymentVO) {
        mPaymentVO = paymentVO

        Glide.with(itemView.context)
            .load("${mPaymentVO!!.icon}")
            .into(itemView.ivPaymentLogo)

        itemView.tvPaymentTitle.text = mPaymentVO!!.title


    }

    init {
        itemView.setOnClickListener {
            mDelegate.onTapPayment()
        }
    }

}