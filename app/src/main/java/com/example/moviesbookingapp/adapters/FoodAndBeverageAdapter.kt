package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.viewholders.FoodAndBeverageViewHolder

class FoodAndBeverageAdapter(val mFoodAndBeverageInsideAdapter: FoodAndBeverageInsideAdapter,val context: Context): RecyclerView.Adapter<FoodAndBeverageViewHolder>() {
    private var  mFoodPrice: ArrayList<Int> = ArrayList()
    private var mTotalPrice:Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAndBeverageViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_food_and_beverage,parent,false)
        return FoodAndBeverageViewHolder(view, mFoodAndBeverageInsideAdapter,context)
    }

    override fun onBindViewHolder(holder: FoodAndBeverageViewHolder, position: Int) {
        holder.setNewData(mTotalPrice)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(keyValues: ArrayList<Pair<String, String>>){
        keyValues.forEach {
            mFoodPrice.add(it.second.toInt())
        }

        mTotalPrice=mFoodPrice.sum()

        notifyDataSetChanged()

    }
}