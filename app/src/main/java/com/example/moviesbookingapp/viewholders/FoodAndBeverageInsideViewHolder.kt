package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_inside_food_and_beverage.view.*

class FoodAndBeverageInsideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mName:Pair<String,String>? = null

    fun setNewData(name: Pair<String, String>) {

        mName = name
        itemView.tvItemNameFood.text = mName!!.first
        itemView.tvItemPriceFood.text = "${mName!!.second} ks"
    }

}