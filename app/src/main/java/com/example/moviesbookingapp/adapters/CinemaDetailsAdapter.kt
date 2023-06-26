package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.example.moviesbookingapp.data.vos.CinemaVO
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.viewholders.CinemaDetailsViewHolder

class CinemaDetailsAdapter(
    val fromCinemaFragment: Boolean,
    val isExpanded: Boolean,
    private val delegate: DateDelegate,
    val context: Context
) : RecyclerView.Adapter<CinemaDetailsViewHolder>() {

    private var mCinemaList: List<CinemaVO> = listOf()
    private var mListVO: List<CinemaInfoVO> = listOf()
    private var mSelectedDate: String? = null
//    private var m

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_cinema_details, parent, false)
        return CinemaDetailsViewHolder(view, isExpanded, context, delegate)
    }

    override fun onBindViewHolder(holder: CinemaDetailsViewHolder, position: Int) {

        if (fromCinemaFragment) {
            holder.bind(mListVO[position])
        } else {
            mSelectedDate?.let {
                holder.bindData(mCinemaList[position], it)
            }

        }
    }

    override fun getItemCount(): Int {
        return if (fromCinemaFragment) {
            mListVO.count()
        } else
            mCinemaList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>, date: String) {
        mCinemaList = cinemaList
        mSelectedDate = date
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewDataForCinema(listInfoVO: List<CinemaInfoVO>) {
        mListVO = listInfoVO
        notifyDataSetChanged()
    }


}