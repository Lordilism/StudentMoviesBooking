package com.example.moviesbookingapp.viewholders

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.data.vos.SeatVO
import com.example.moviesbookingapp.delegates.SeatSelect
import kotlinx.android.synthetic.main.viewholder_seat.view.*

class SeatViewHolder(itemview: View, val mDelegate: SeatSelect) :
    RecyclerView.ViewHolder(itemview) {

    private var mSeat: SeatVO? = null
    private var mIsSelected: Boolean = false
//    private var isAvailable: Boolean = true


    init {
        itemview.ivSeat.setOnClickListener {

            if (mSeat?.type == "available"){
                mIsSelected = !mIsSelected
                when(mIsSelected){
                    false->{
                        itemview.ivSeat.setColorFilter(Color.WHITE)
                        mSeat?.let { seatVO -> mDelegate.onTapSeat(seatVO,false) }
                    }
                    true->{
                        itemview.ivSeat.setColorFilter(Color.GREEN)
                        mSeat?.let { seatVO -> mDelegate.onTapSeat(seatVO,true) }
                    }
                }
            }


        }




    }


    fun bindData(seat: SeatVO, position: Int) {
        mSeat = seat


        when (mSeat!!.type) {
            "taken" -> {
                itemView.ivSeat.setColorFilter(Color.GRAY)
            }

            "text", "space" -> {
                itemView.ivSeat.visibility = View.INVISIBLE
            }
            "available"->{

            }

        }

    }


}

