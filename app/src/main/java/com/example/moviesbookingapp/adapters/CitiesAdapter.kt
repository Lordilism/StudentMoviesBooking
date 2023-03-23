package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.CityVo
import com.example.moviesbookingapp.delegates.CitiesDelegate
import com.example.moviesbookingapp.viewholders.CitiesViewHolder
import kotlinx.android.synthetic.main.viewholder_cities.view.*

class CitiesAdapter(private val mDelegate: CitiesDelegate) : RecyclerView.Adapter<CitiesViewHolder>() {
    private var mCitiesList:List<CityVo> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cities, parent, false)
        return CitiesViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        if (mCitiesList.isNotEmpty()){
            val city = mCitiesList[position]
            holder.bindData(mCitiesList[position])
        }



    }

    override fun getItemCount(): Int {
        return mCitiesList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(Cities: List<CityVo>){
        mCitiesList = Cities
        notifyDataSetChanged()

    }
}