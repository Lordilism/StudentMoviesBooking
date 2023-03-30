package com.example.moviesbookingapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.CinemaDetailsAdapter
import com.example.moviesbookingapp.adapters.DateAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.delegates.SelectDateDelegate
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.activity_date_select.*
import java.util.*

class DateSelectActivity : AppCompatActivity(), DateDelegate, SelectDateDelegate {
    lateinit var mDateAdapter: DateAdapter
    lateinit var mCinemaDetailsAdapter: CinemaDetailsAdapter
    private var mMovieModel: MovieModel = MovieModelImpl
    private var isExpanded = false
    private var mcolorList: ArrayList<TimeSlotColor>? = null

    companion object {
        const val CINEMA_TIME_SLOT_STATUS = "cinema_timeslot_status"
        fun newIntent(context: Context): Intent {
            return Intent(context, DateSelectActivity::class.java)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_select)
        NavigateBack()
        setUpDateSelect()
        setUpAboutCinema()





    }

    private fun NavigateBack() {
        btnBackFromDateSelect.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpAboutCinema() {
        mCinemaDetailsAdapter = CinemaDetailsAdapter(false, this, this)
        rvAboutCinemas.adapter = mCinemaDetailsAdapter
        rvAboutCinemas.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpDateSelect() {

        mDateAdapter = DateAdapter(getDayofWeeks(), getMonth(), getDays(), getYear(), this)
        mDateAdapter.context = this.applicationContext
        rvDate.adapter = mDateAdapter
        mDateAdapter.notifyDataSetChanged()


        rvDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    override fun onTapDate() {
        startActivity(SeatSelectActivity.newIntent(this))
    }

    override fun onTapDetails() {

    }

    fun getDayofWeeks(): List<java.util.Date> {
        val calendar = Calendar.getInstance()
        var daysOfWeek = mutableListOf<java.util.Date>()
        for (i in 0..13) {
            daysOfWeek.add(calendar.time)
            calendar.add(Calendar.DAY_OF_WEEK, 1)
        }
        return daysOfWeek
    }

    fun getMonth(): List<java.util.Date> {
        val calendar = Calendar.getInstance()
        val nameOfMonthandDate = mutableListOf<java.util.Date>()
        for (i in 0..13) {
            nameOfMonthandDate.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)

        }
        return nameOfMonthandDate

    }

    fun getDays(): List<java.util.Date> {
        val calendar = Calendar.getInstance()
        val days = mutableListOf<java.util.Date>()
        for (i in 0..13) {
            days.add(calendar.time)
            calendar.add(Calendar.DATE, 1)
        }
        return days
    }

    fun getYear(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.YEAR)
    }

    override fun onTapSelectDate(param: String) {
        Snackbar.make(window.decorView, param, Snackbar.LENGTH_INDEFINITE).show()

        mMovieModel.getCinemaTimeslots(
            "Bearer ${mMovieModel?.getOtp(201)?.token}", param,
            onSuccess = {
                mCinemaDetailsAdapter.setNewData(it,)
            },
            onFailure = {

            }

        )


    }


}
