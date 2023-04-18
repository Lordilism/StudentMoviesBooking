package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.SnackVO
import com.example.moviesbookingapp.delegates.SnackSelect
import com.example.moviesbookingapp.viewholders.FoodViewHolder

class FoodAdapter(val mDelegateSnack: SnackSelect): RecyclerView.Adapter<FoodViewHolder>() {
    private var mSnackVO: List<SnackVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_food,parent,false)
        return FoodViewHolder(view,mDelegateSnack)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        mSnackVO.let {
            holder.setNewData(it[position])
        }
    }

    override fun getItemCount(): Int {
        return mSnackVO.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(snackVO: List<SnackVO>){

        mSnackVO = snackVO
        notifyDataSetChanged()





    }
}