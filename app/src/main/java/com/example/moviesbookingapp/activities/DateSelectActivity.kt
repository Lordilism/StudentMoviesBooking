package com.example.moviesbookingapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.CinemaDetailsAdapter
import com.example.moviesbookingapp.adapters.DateAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.delegates.SelectDateDelegate
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_date_select.*
import java.util.*

class DateSelectActivity : AppCompatActivity(), DateDelegate, SelectDateDelegate {
    lateinit var mDateAdapter: DateAdapter
    lateinit var mCinemaDetailsAdapter: CinemaDetailsAdapter
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mMovieId: String
    private var isExpanded = false
    private var mcolorList: ArrayList<TimeSlotColor>? = null
    private var mMovieVO: MovieVO? = null

    companion object {
        const val CINEMA_TIME_SLOT_STATUS = "cinema_timeslot_status"
        const val IE_MOVIE_VO = "IE_MOVIE_VO"

        fun newIntent(context: Context, mMovie: MovieVO): Intent {
            val intent = Intent(context, DateSelectActivity::class.java)
            intent.putExtra(IE_MOVIE_VO, mMovie)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_select)
        mMovieVO = intent.getSerializableExtra(IE_MOVIE_VO) as MovieVO

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
        mCinemaDetailsAdapter = CinemaDetailsAdapter(false, false, this, this)
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

    override fun onTapDate(
        cinemaTimeSlotId: Int,
        bookingDate: String,
        startTime: String?,
        cinemaName: String?
    ) {
        Log.d("info", "$cinemaTimeSlotId:: $bookingDate")
        val slotId = cinemaTimeSlotId
        val date = bookingDate

        mMovieModel.getSeat(
            "Bearer ${mMovieModel.getOtp(201)?.token}",
            onSuccess = {
                startActivity(
                    SeatSelectActivity.newIntent(
                        this,
                        it,
                        date,
                        slotId,
                        mMovieVO?.id.toString(),
                        cinemaName!!,
                        startTime!!
                    )
                )

            },
            onFailure = {

            }, dayTimeSlotId = slotId,
            bookingDate = date
        )
    }

    override fun onTapDetails(mCinemaInfoVO: CinemaInfoVO) {

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

    override fun onTapSelectDate(selectedDate: String) {
//        Snackbar.make(window.decorView, selectedDate, Snackbar.LENGTH_INDEFINITE).show()

        mMovieModel.getCinemaTimeslots(
            "Bearer ${mMovieModel.getOtp(201)?.token}", selectedDate,
            onSuccess = {
                mCinemaDetailsAdapter.setNewData(it, selectedDate)
            },
            onFailure = {

            }

        )


    }


}
