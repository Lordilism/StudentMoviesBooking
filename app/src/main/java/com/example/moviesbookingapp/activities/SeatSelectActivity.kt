package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.SeatAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.SeatVO
import com.example.moviesbookingapp.delegates.SeatSelect
import kotlinx.android.synthetic.main.activity_seat_select.*
import java.io.Serializable

class SeatSelectActivity : AppCompatActivity(), SeatSelect {
//    private lateinit var movieNameForCheckout: String
//    private lateinit var time: String
    private var mMovieModel: MovieModel = MovieModelImpl
    lateinit var mSeatAdapter: SeatAdapter
    private var mTicketPrice: MutableList<Int> = mutableListOf()
    private var mNumberOfTicket: MutableList<SeatVO> = mutableListOf()
    private var mSeatNameList: MutableList<String> = mutableListOf()
    private lateinit var mMovieID: String

    companion object {
        const val IE_SEAT_LIST = "IE_SEAT_LIST"
        const val IE_BOOKING_DATE = "IE_BOOKING_DATE"
        const val IE_SLOT_ID = "IE_SLOT_ID"
        const val IE_MOVIE_ID = "IE_MOVIE_ID"
        const val IE_CINEMA_NAME = "IE_CINEMA_NAME"
        const val IE_CINEMA_START_TIME = "IE_CINEMA_START_TIME"

        fun newIntent(
            context: Context,
            seatVoList: MutableList<MutableList<SeatVO>>,
            bookingDate: String,
            slotId: Int,
            mMovieId: String,
            cinemaName: String,
            startTime: String,

            ): Intent {
            val intent = Intent(context, SeatSelectActivity::class.java)
            intent.putExtra(IE_SEAT_LIST, seatVoList as Serializable)
            intent.putExtra(IE_BOOKING_DATE, bookingDate)
            intent.putExtra(IE_SLOT_ID,slotId)
            intent.putExtra(IE_MOVIE_ID,mMovieId)
            intent.putExtra(IE_CINEMA_NAME,cinemaName)
            intent.putExtra(IE_CINEMA_START_TIME,startTime)
            return intent


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)
        val seatList =
            intent.getSerializableExtra(IE_SEAT_LIST) as MutableList<MutableList<SeatVO>>?

        val listSeatVO = seatTest(seatList!!).flatten()

        val booingDate = intent.getStringExtra(IE_BOOKING_DATE)   // date
        Log.d("date" , "$booingDate")

        val timeSlotId = intent.getIntExtra(IE_SLOT_ID,1)  //slotId
        Log.d("slotID" , "$timeSlotId")

        val movieId = intent.getStringExtra(IE_MOVIE_ID)            //movieID
        Log.d("movieID", "$movieId")

        val cinemaName= intent.getStringExtra(IE_CINEMA_NAME)

        val cinemaStartTime = intent.getStringExtra(IE_CINEMA_START_TIME)
        setUpSeatRecyclerView(listSeatVO)

//        val cinemaTimeSlotId = intent.getIntExtra(IE_CINEMA_ID, 1)
//        Log.i("CinemaId", cinemaTimeSlotId.toString())
//
//        val bookingDate = intent.getStringExtra(IE_BOOKING_DATE)  //to send
//        Log.i("booking", bookingDate.toString())
//
//        time =
//            intent.getStringExtra(IE_CINEMA_TIME)
//                .toString()                                    //to send
//        Log.i("time", time.toString())
//
//        movieNameForCheckout =
//            intent.getStringExtra(IE_MOVIE_NAME_CHECKOUT).toString()            // to send
//        Log.i("movieName", movieNameForCheckout.toString())
//
//        mMovieID = intent.getStringExtra(IE_MOVIE_ID_ID).toString()


        setUpListeners(bookingDate = booingDate!!, slotId = timeSlotId,movieId = movieId!!,cinemaName!!,cinemaStartTime!!)


    }

    private fun setUpListeners(
        bookingDate: String,
        slotId: Int,
        movieId: String,
        cinemaName: String,
        cinemaStartTime: String
    ) {
        btnBuyTickets.setOnClickListener {
            setUpbtnBuy(bookingDate = bookingDate, timeSlotID = slotId, mMovieID = movieId,cinemaName ,cinemaStartTime )
        }
    }


    private fun setUpSeatRecyclerView(seatList: List<SeatVO>) {

        mSeatAdapter = SeatAdapter(this)
        rvSeatVertical.adapter = mSeatAdapter
        rvSeatVertical.layoutManager = GridLayoutManager(this, 18)

        mSeatAdapter.setNewData(seatList)
    }


    private fun setUpbtnBuy(
        bookingDate: String,
        timeSlotID: Int,
        mMovieID: String,
        cinemaName: String,
        cinemaStartTime: String
    ) {
        startActivity(
            GrabBiteActivity.newIntent(
                this,
                mSeatNameList,
                bookingDate,timeSlotID.toString(),
                mMovieID,
                cinemaName,
                cinemaStartTime
            )
        )
        finish()
    }

    private fun seatTest(networkListOfSeat: MutableList<MutableList<SeatVO>>): MutableList<MutableList<SeatVO>> {
        val space = SeatVO(null, null, null, null, "space")
        val listHoriRow: MutableList<SeatVO> = mutableListOf()
        for (i in networkListOfSeat.indices) {
            networkListOfSeat[i].let {
                it.add(5, space)
                it.add(6, space)
                it.add(11, space)
                it.add(12, space)
            }

            val listForRowSpace: MutableList<SeatVO> = mutableListOf()
            for (j in networkListOfSeat[i].indices) {
                when (j) {
                    5 -> listForRowSpace.add(5, space)
                    6 -> listForRowSpace.add(6, space)
                    11 -> listForRowSpace.add(11, space)
                    12 -> listForRowSpace.add(12, space)
                    else -> listForRowSpace.add(networkListOfSeat[i][j])
                }

            }
        }

        for (i in 0..17) {
            listHoriRow.add(i, space)
        }
        networkListOfSeat.add(3, listHoriRow)
        networkListOfSeat.add(8, listHoriRow)
        networkListOfSeat.add(13, listHoriRow)
        networkListOfSeat.add(18, listHoriRow)
        return networkListOfSeat
    }


    override fun onTapSeat(seatForTapping: SeatVO, isAddOrNot: Boolean) {

        if (isAddOrNot) {
            var value = 0

            seatForTapping.price?.let { mTicketPrice.add(it) }
            mNumberOfTicket.add(value, seatForTapping)
            mSeatNameList.add(mNumberOfTicket[value].seatName ?: "")
            value = value.inc()

            tvSeatSelectedNumber.text = "${mNumberOfTicket.count()} tickets"
            tvTicketPrice.text = "${mTicketPrice.sum()} Ks"
        } else {


            mTicketPrice.removeLastOrNull()
            mNumberOfTicket.removeLastOrNull()
            tvTicketPrice.text = "${mTicketPrice.sum()} Ks"

            tvSeatSelectedNumber.text = "${mNumberOfTicket.count()} tickets"


        }

        Log.d("seatList", mSeatNameList.toString())
        Log.d("numberOfTicket", mNumberOfTicket.toString())


    }
}