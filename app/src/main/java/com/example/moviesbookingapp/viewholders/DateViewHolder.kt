package com.example.moviesbookingapp.viewholders

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.delegates.SelectDateDelegate

@SuppressLint("ResourceAsColor")
class DateViewHolder(itemView: View, mDelegate: SelectDateDelegate, position: Int) :
    RecyclerView.ViewHolder(itemView) {
    //first call

    private var paramForApi: String? = null

    init {



    }

    fun bindData(param: String) {
        paramForApi = param

    }


}