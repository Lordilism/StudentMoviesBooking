package com.example.moviesbookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.PaymentVO
import com.example.moviesbookingapp.delegates.PaymentDelegate
import com.example.moviesbookingapp.viewholders.PaymentViewHolder

class PaymentAdapter(val mDelegate: PaymentDelegate): RecyclerView.Adapter<PaymentViewHolder>() {
    private var mPaymentList = listOf<PaymentVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_payment,parent,false)
        return PaymentViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bindData(mPaymentList[position])

    }

    override fun getItemCount(): Int {
        return mPaymentList.count()

    }

    fun setNewData(payment: List<PaymentVO>) {
        mPaymentList = payment
        notifyDataSetChanged()


    }
}