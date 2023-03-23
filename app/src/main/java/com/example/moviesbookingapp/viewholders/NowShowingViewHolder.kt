package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.delegates.MoviesDelegate
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_now_showing.view.*
import java.time.LocalDate

class NowShowingViewHolder(
    itemView: View,
    var isNowShowing: Boolean,
    private val mDelegateMovies: MoviesDelegate
) :

    RecyclerView.ViewHolder(itemView) {
    private var mMovie: MovieVO? = null

    init {
        when (isNowShowing) {
            true -> {
                itemView.tvDueDate.visibility = View.GONE
            }
            false -> itemView.tvDueDate.visibility = View.VISIBLE
        }

        itemView.setOnClickListener {
            mMovie?.let {
                mDelegateMovies.onTapMovies(it.id.toString())
            }

        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImageFromNowShowing)

        itemView.tvTitleMovies.text = movie.originalTitle


        movie.releaseDate?.let {
            itemView.tvDueDate.text = movie.formatDate(it,forDetails = false)
        }

    }

//    fun formatDate(Date: String): String {
//        val responseDate = Date
//
//        val date = LocalDate.parse(responseDate)
//        val dayonMonth = date.dayOfMonth
//        val monthString = date.month.toString()
//        var str = String()
//        for (index in 0..2) {
//            str += monthString[index]
//        }
//
//        val suffixes =
//            mapOf(1 to "st", 2 to "nd", 3 to "rd", 21 to "st", 22 to "nd", 23 to "rd", 31 to "st")
//        val default = suffixes.getOrDefault(dayonMonth, "th")
//
//        val datetoView = "${dayonMonth} ${default}\n${str}"
//        return datetoView
//
//    }


}