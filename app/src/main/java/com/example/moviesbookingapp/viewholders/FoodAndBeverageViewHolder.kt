package com.example.moviesbookingapp.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.adapters.FoodAndBeverageInsideAdapter
import kotlinx.android.synthetic.main.viewholder_food_and_beverage.view.*

class FoodAndBeverageViewHolder(
    itemView: View,
    mFoodAndBeverageInsideAdapter: FoodAndBeverageInsideAdapter,
    context: Context
) : RecyclerView.ViewHolder(itemView) {
    private var mPriceTotal:Int = 0
    init {

        itemView.rvInsideFoodAndBeverage.adapter =mFoodAndBeverageInsideAdapter
        itemView.rvInsideFoodAndBeverage.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }

    fun setNewData(totalPrice: Int) {
        mPriceTotal = totalPrice

        itemView.totalFoodPrice.text =" ${mPriceTotal.toString()} Ks"


    }
}