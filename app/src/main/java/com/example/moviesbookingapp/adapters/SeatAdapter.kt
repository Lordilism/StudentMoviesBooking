package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.SeatVO
import com.example.moviesbookingapp.delegates.SeatSelect
import com.example.moviesbookingapp.viewholders.SeatViewHolder
import kotlinx.android.synthetic.main.viewholder_seat.view.*

class SeatAdapter(val mDelegate: SeatSelect) : RecyclerView.Adapter<SeatViewHolder>() {

    private var mSeatList: List<SeatVO> = listOf()


//    private var mOneDimenList: MutableList<SeatVO> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_seat, parent, false)
        return SeatViewHolder(view, mDelegate)
    }


    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {

        holder.bindData(mSeatList[position],holder.adapterPosition)

    }

    override fun getItemCount(): Int {
        return mSeatList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(seatList: List<SeatVO>) {
        mSeatList = seatList
        notifyDataSetChanged()


    }
}