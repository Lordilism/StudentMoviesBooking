package com.example.moviesbookingapp.viewholders

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.data.vos.TimeSlotVO
import com.example.moviesbookingapp.delegates.DateDelegate
import kotlinx.android.synthetic.main.viewholder_cinema_date.view.*

class ShowTimeViewHolder(
    itemView: View,
    val delegate: DateDelegate,
    val data: ArrayList<TimeSlotColor>
) :
    RecyclerView.ViewHolder(itemView) {
    private var mTimeslot: TimeSlotVO? = null
    private var mBookingdate: String? = null
    private var mCinemaNameStr: String? = null

    fun bindData(timeslot: TimeSlotVO, bookingDate: String, mCinemaName: String?) {
        mTimeslot = timeslot
        mBookingdate = bookingDate
        mCinemaNameStr = mCinemaName
        val pair = mutableMapOf<Int?, String?>()
        data.forEach {
            pair.put(it.id, it.color)
        }
        val color = pair.getOrDefault(mTimeslot?.status, "")

        val shape = GradientDrawable()
        shape.shape= GradientDrawable.RECTANGLE
        shape.setColor(itemView.resources.getColor(R.color.colorPrimary,null))
        shape.cornerRadius = itemView.resources.getDimension(R.dimen.margin_small)
        shape.setStroke(2,Color.parseColor(color))
        itemView.llCinemaTime.background = shape

        itemView.tvShowTime.text = mTimeslot?.startTime



    }

    init {
        itemView.setOnClickListener {


            mTimeslot?.cinemaDayTimeslotId?.let { id ->
                delegate.onTapDate(
                    id, mBookingdate!!,
                    mTimeslot!!.startTime,
                    mCinemaNameStr
                )
            }


        }
    }
}