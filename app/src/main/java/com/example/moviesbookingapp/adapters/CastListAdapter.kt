package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.CastersVO
import com.example.moviesbookingapp.viewholders.CastListViewHolder

class CastListAdapter:RecyclerView.Adapter<CastListViewHolder>() {
    private var mCasterList:List<CastersVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cast_list,parent,false)
        return CastListViewHolder(view)

    }

    override fun onBindViewHolder(holder: CastListViewHolder, position: Int) {
        holder.bindData(mCasterList[position])
    }

    override fun getItemCount(): Int {
        return mCasterList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(CasterList: List<CastersVO>){
        mCasterList = CasterList
        notifyDataSetChanged()
    }


}