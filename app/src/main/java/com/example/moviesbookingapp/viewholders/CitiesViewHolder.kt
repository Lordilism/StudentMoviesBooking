package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.data.vos.CityVo
import com.example.moviesbookingapp.delegates.CitiesDelegate
import kotlinx.android.synthetic.main.viewholder_cities.view.*

class CitiesViewHolder(itemView: View,mDelegate: CitiesDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mCityVo:CityVo?= null

    init {
        itemView.setOnClickListener {
            mDelegate.onTapCities(mCityVo?.name)
        }
    }

    fun bindData(city: CityVo){
        mCityVo = city
        itemView.tvCitiesNameFromPickLocation.text = city.name

    }

}