package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.CinemaVO
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.viewholders.CinemaDetailsViewHolder
import kotlinx.android.synthetic.main.viewholder_cinema_details.view.*

class CinemaDetailsAdapter(val isExpanded:Boolean,private val delegate:DateDelegate,val context: Context): RecyclerView.Adapter<CinemaDetailsViewHolder>() {

    private var mCinemaList: List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cinema_details,parent,false)
        return CinemaDetailsViewHolder(view,isExpanded,context,delegate)
    }

    override fun onBindViewHolder(holder: CinemaDetailsViewHolder, position: Int) {
        if (mCinemaList.isNotEmpty()){
            holder.bindData(mCinemaList[position])

        }
    }

    override fun getItemCount(): Int {
        return mCinemaList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>){
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }


}