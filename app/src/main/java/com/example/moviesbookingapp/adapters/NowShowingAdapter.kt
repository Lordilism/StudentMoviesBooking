package com.example.moviesbookingapp.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.delegates.MoviesDelegate
import com.example.moviesbookingapp.viewholders.NowShowingViewHolder
import kotlinx.android.synthetic.main.viewholder_now_showing.view.*

class NowShowingAdapter(val isNowShowwing:Boolean,private val mDelegateMovies:MoviesDelegate) : RecyclerView.Adapter<NowShowingViewHolder>() {
    private var moviesData:List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_now_showing, parent, false)
        return NowShowingViewHolder(view,isNowShowwing,mDelegateMovies)

    }


    override fun getItemCount(): Int {
        return moviesData.count()
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {

        if (moviesData.isNotEmpty()){

           holder.bindData(moviesData[position])

        }



    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(moviesList:List<MovieVO>){
        moviesData = moviesList
        notifyDataSetChanged()
    }


}