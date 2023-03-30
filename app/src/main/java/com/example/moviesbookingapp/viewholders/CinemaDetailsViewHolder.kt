package com.example.moviesbookingapp.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.adapters.ShowTimeAdapter
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CinemaVO
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.delegates.DateDelegate
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.viewholder_cinema_details.view.*

class CinemaDetailsViewHolder(
    itemView: View,
    var isExpanded: Boolean,
    val context: Context,
    val delegate: DateDelegate
) : RecyclerView.ViewHolder(itemView) {
    lateinit var mShowTimeAdapter: ShowTimeAdapter
    private var mCinema: CinemaVO? = null
    private var mMovieModel = MovieModelImpl


    private var color: LinkedTreeMap<Int, String> = LinkedTreeMap<Int, String>()

    init {
        val value =
            mMovieModel.getConfig("cinema_timeslot_status")?.value as? ArrayList<*>

        val data = value?.let {
            linkedtoGson(it)
        }
        isExpanded = false
        itemView.rlHideView.visibility = View.GONE
        setUpRecyclerViewTime(data as ArrayList<TimeSlotColor>)
        itemView.setOnClickListener {
            setUpVisibilityOfLayout()
        }
        itemView.tvSeeDetals.setOnClickListener {
            delegate.onTapDetails()
        }

    }

    private fun setUpRecyclerViewTime(data: ArrayList<TimeSlotColor>) {
        mShowTimeAdapter = ShowTimeAdapter(delegate,data)
        itemView.rvCinemaShowdate.adapter = mShowTimeAdapter
        itemView.rvCinemaShowdate.layoutManager =
            GridLayoutManager(this.context, 3, GridLayoutManager.VERTICAL, false)
    }

    private fun setUpVisibilityOfLayout() {
        isExpanded = !isExpanded
        itemView.apply {
            rlHideView.visibility = if (isExpanded) View.VISIBLE else View.GONE

        }

    }

    fun bindData(cinema: CinemaVO) {
        mCinema = cinema

        cinema.timeSlots?.let {
            mShowTimeAdapter.setNewData(it)
        }

        itemView.tvCinemas.text = mCinema?.cinema

    }

    private fun linkedtoGson(value: ArrayList<*>): ArrayList<TimeSlotColor> {


        val desiredValue = arrayListOf<TimeSlotColor>()


        for (data in value!!) {
            val treeMap = data as LinkedTreeMap<*, *>
            val timeSlotColor = Gson().fromJson(Gson().toJson(treeMap), TimeSlotColor::class.java)

            desiredValue.add(timeSlotColor)
        }
        return desiredValue
    }


}