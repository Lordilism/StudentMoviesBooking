package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.data.vos.TimeSlotVO
import com.example.moviesbookingapp.delegates.DateDelegate
import kotlinx.android.synthetic.main.viewholder_cinema_date.view.*

class ShowTimeViewHolder(itemView: View, val delegate: DateDelegate,val  data: ArrayList<TimeSlotColor>) :
    RecyclerView.ViewHolder(itemView) {
    private var mTimeslot : TimeSlotVO? = null

    fun bindData(timeslot: TimeSlotVO) {
        mTimeslot = timeslot
        val pair = mutableMapOf<Int?,String?>()
        data.forEach {
           pair.put(it.id,it.color)
        }
        val color = pair.getOrDefault(mTimeslot?.status,"")
        color?.toColorInt()?.let { itemView.rlViewholder.setBackgroundColor(it) }

        itemView.tvShowTime.text = mTimeslot?.startTime

    }

    init {
        itemView.setOnClickListener {

            delegate.onTapDate()

        }
    }
}