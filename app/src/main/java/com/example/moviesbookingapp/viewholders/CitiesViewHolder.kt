package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.CityVo
import com.example.moviesbookingapp.delegates.CitiesDelegate
import com.example.moviesbookingapp.utils.BASE_URL
import kotlinx.android.synthetic.main.viewholder_cities.view.*

class CitiesViewHolder(itemView: View,mDelegate: CitiesDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mCityVo:CityVo?= null

    init {
        itemView.setOnClickListener {
            mDelegate.onTapCities()
        }
    }

    fun bindData(city: CityVo){
        itemView.tvCitiesNameFromPickLocation.text = city.name

    }

}