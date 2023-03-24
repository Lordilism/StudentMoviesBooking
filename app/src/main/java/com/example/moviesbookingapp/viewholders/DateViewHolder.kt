package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.delegates.SelectDateDelegate

class DateViewHolder(itemView: View, mDelegate: SelectDateDelegate) : RecyclerView.ViewHolder(itemView) {
    private var paramForApi: String? = null
    init {
        itemView.setOnClickListener{
            paramForApi?.let { it1 -> mDelegate.onTapSelectDate(it1) }
        }
    }
    fun bindData(param:String ){
        paramForApi = param
    }



}