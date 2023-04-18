package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.SnackVO
import com.example.moviesbookingapp.delegates.SnackSelect
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_food.view.*

class FoodViewHolder(itemView: View,val mDelegate : SnackSelect) : RecyclerView.ViewHolder(itemView) {
    private var mSnackVO:SnackVO? = null

    init {
        var initialState = true
        var initialValue = 1


        itemView.btnAdd.setOnClickListener {
            initialState=!initialState
            it.visibility = View.GONE
            mSnackVO?.let { snack -> mDelegate.onTapAdd(snack,true) }
        }

        itemView.ivIncrease.setOnClickListener {

            initialValue++
            itemView.numberOfFood.text = initialValue.toString()
            mSnackVO?.let { snackVO -> mDelegate.onTapAdd(snackVO,true) }
        }

        itemView.ivDecrease.setOnClickListener {
            if (initialValue>1){

                initialValue--
                itemView.numberOfFood.text = initialValue.toString()
                mSnackVO?.let { snackVO -> mDelegate.onTapAdd(snackVO,false) }


            }else{

                itemView.btnAdd.visibility = View.VISIBLE
                mSnackVO?.let { snack -> mDelegate.onTapAdd(snack,false) }

            }

        }
    }

    fun setNewData(snackVO: SnackVO){
        mSnackVO = snackVO

        Glide.with(itemView.context)
            .load(mSnackVO!!.image)
            .into(itemView.ivFoodGrab)

        itemView.tvFoodName.text = mSnackVO!!.name
        itemView.tvFoodPriceValue.text = "${mSnackVO!!.price} Ks"

    }

}