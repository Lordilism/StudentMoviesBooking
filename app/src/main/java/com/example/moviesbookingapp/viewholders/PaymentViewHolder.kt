package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.delegates.PaymentDelegate

class PaymentViewHolder(itemView: View, mDelegate: PaymentDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapPayment()
        }
    }

}