package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.data.vos.TimeSlotVO
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.viewholders.ShowTimeViewHolder

class ShowTimeAdapter(private val delegate: DateDelegate,val  data: ArrayList<TimeSlotColor>) :
    RecyclerView.Adapter<ShowTimeViewHolder>() {
    private var mTimeSlots: List<TimeSlotVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_cinema_date, parent, false)
        return ShowTimeViewHolder(view, delegate,data)
    }

    override fun onBindViewHolder(holder: ShowTimeViewHolder, position: Int) {
        if (mTimeSlots.isNotEmpty()) {
            holder.bindData(mTimeSlots[position])
        }
    }

    override fun getItemCount(): Int {
        return mTimeSlots.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeSlot: List<TimeSlotVO>) {
        mTimeSlots = timeSlot
        notifyDataSetChanged()
    }
}