package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.viewholders.DateViewHolder
import kotlinx.android.synthetic.main.viewholder_date.view.*
import java.text.SimpleDateFormat

class DateAdapter(
    private val daysOfWeeks: List<java.util.Date>,
    val month: List<java.util.Date>,
    val days: List<java.util.Date>
) :
    RecyclerView.Adapter<DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_date, parent, false)

        return DateViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val dayOfWeeks = SimpleDateFormat("EEE").format(daysOfWeeks[position])

        val month = SimpleDateFormat("MMM").format(month[position])

        val days = SimpleDateFormat("dd").format(days[position])



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