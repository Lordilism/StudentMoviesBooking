package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.delegates.SelectDateDelegate
import com.example.moviesbookingapp.viewholders.DateViewHolder
import kotlinx.android.synthetic.main.viewholder_date.view.*
import java.text.SimpleDateFormat

class DateAdapter(
    private val daysOfWeeks: List<java.util.Date>,
    private val month: List<java.util.Date>,
    private val days: List<java.util.Date>,
    private val year: Int,
    private val mDelegate: SelectDateDelegate
) :
    RecyclerView.Adapter<DateViewHolder>() {
    var context: Context? =  null
    private var selectedPosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_date, parent, false)

        return DateViewHolder(view, mDelegate, selectedPosition)
    }

    @SuppressLint("SimpleDateFormat", "ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {


        //Weeks Days
        val dayOfWeeks = SimpleDateFormat("EEE").format(daysOfWeeks[position])
        //Month Name
        val month = SimpleDateFormat("MMM").format(month[position])
        //Month Name To Number
        val monthbyInteger = when (month) {
            "Jan" -> "01"
            "Feb" -> "02"
            "Mar" -> "03"
            "Apr" -> "04"
            "May" -> "05"
            "Jun" -> "06"
            "Jul" -> "07"
            "Aug" -> "08"
            "Sep" -> "09"
            "Oct" -> "10"
            "Nov" -> "11"
            else -> "12"
        }

        val days = SimpleDateFormat("dd").format(days[position])

        val year = year
        val mon = monthbyInteger
        //for api Query
        val param = "$year-$mon-$days"
        holder.bindData(param)

        Log.i("days", dayOfWeeks)
        when (position) {
            0 -> {
                holder.itemView.tvDaystatus.text = "Today"
                holder.itemView.tvNameMonth.text = "${month}\n${days}"
            }
            1 -> {
                holder.itemView.tvDaystatus.text = "Tomorrow"
                holder.itemView.tvNameMonth.text = "${month}\n${days}"
            }
            else -> {
                holder.itemView.tvDaystatus.text = dayOfWeeks
                holder.itemView.tvNameMonth.text = "${month}\n${days}"
            }
        }

        val backgroundColor = context?.let { ContextCompat.getColor(it,R.color.colorAccent) }
        val default = context?.let { ContextCompat.getColor(it,R.color.colorBackgrounddate) }
        val selectedColorState = backgroundColor?.let { ColorStateList.valueOf(it) }
        val defaultColorState = default?.let { ColorStateList.valueOf(it) }

        if (selectedPosition == position){
            holder.itemView.dateSlotColor.backgroundTintList = selectedColorState
            mDelegate.onTapSelectDate(param)

        }else{
            holder.itemView.dateSlotColor.backgroundTintList = defaultColorState
        }

        holder.itemView.dateSlotColor.setOnClickListener {
            val lastEnteredItem = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastEnteredItem)

            mDelegate.onTapSelectDate(param)
            holder.itemView.dateSlotColor.backgroundTintList = selectedColorState
        }




    }


    override fun getItemCount(): Int {
        return daysOfWeeks.count()

    }


}