package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.viewholders.FoodAndBeverageInsideViewHolder

class FoodAndBeverageInsideAdapter: RecyclerView.Adapter<FoodAndBeverageInsideViewHolder>() {
    private  var mFoodNameAndPrice: ArrayList<Pair<String, String>> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodAndBeverageInsideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_inside_food_and_beverage,parent,false)
        return FoodAndBeverageInsideViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAndBeverageInsideViewHolder, position: Int) {
        holder.setNewData(mFoodNameAndPrice[position])

    }

    override fun getItemCount(): Int {
       return mFoodNameAndPrice.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(nameAndPrice: ArrayList<Pair<String, String>>){
        mFoodNameAndPrice = nameAndPrice
        notifyDataSetChanged()

    }
}