package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.delegates.SelectDateDelegate
import com.example.moviesbookingapp.viewholders.DateViewHolder
import kotlinx.android.synthetic.main.viewholder_date.view.*
import java.text.SimpleDateFormat

class DateAdapter(
    private val daysOfWeeks: List<java.util.Date>,
    val month: List<java.util.Date>,
    val days: List<java.util.Date>,
    val year: Int,
    private val mDelegate: SelectDateDelegate
) :
    RecyclerView.Adapter<DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_date, parent, false)

        return DateViewHolder(view,mDelegate)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val dayOfWeeks = SimpleDateFormat("EEE").format(daysOfWeeks[position])

        val month = SimpleDateFormat("MMM").format(month[position])


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


    }

    override fun getItemCount(): Int {
        return daysOfWeeks.count()
    }
}