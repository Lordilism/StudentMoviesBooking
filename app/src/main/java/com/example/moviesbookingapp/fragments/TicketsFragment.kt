package com.example.moviesbookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.activities.CheckOutActivity
import com.example.moviesbookingapp.adapters.TicketTabAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CheckOutBodySnack
import com.example.moviesbookingapp.data.vos.TicketForDatabase
import com.example.moviesbookingapp.delegates.TicketDelegate
import kotlinx.android.synthetic.main.fragment_tickets.view.*


class TicketsFragment : Fragment(), TicketDelegate {
    private val mMovieModel: MovieModel = MovieModelImpl

    private val mSelectedFood = mutableListOf<String>()
    private val mSelectedFoodPrice = mutableListOf<String>()
    private var mSelectedSeatName = mutableListOf<String>()
    private lateinit var mMovieName: String
    private lateinit var mCinemaName: String
    private lateinit var mMovieID:String
    private var mCinemaID = 1
    private lateinit var mCinemaStartTime: String
    private var mCheckoutBodySnack = listOf<CheckOutBodySnack>()
    private lateinit var mTicketID:String

    companion object {
        const val IE_TO_CHECKOUT = "IE_TO_CHECKOUT"
    }

    lateinit var mTicketTabAdapter: TicketTabAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mTicketTabAdapter = TicketTabAdapter(this)
        view.rvFromTicketTab.adapter = mTicketTabAdapter
        view.rvFromTicketTab.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL, false
        )
        mMovieModel.getAllTickets()?.observe(viewLifecycleOwner){
            mTicketTabAdapter.setNewData(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onTapTicket(mTicketForDatabase: TicketForDatabase) {
        mTicketForDatabase.ticketCheckout?.snacks?.forEach {
            it.name?.let { name -> mSelectedFood.add(name) }
            it.price?.let { price -> mSelectedFoodPrice.add(price.toString()) }
        }
        mTicketForDatabase.ticketCheckout?.seat.let { seatNames ->
            mSelectedSeatName = seatNames?.split(",") as MutableList<String>
        }
        mMovieName = mTicketForDatabase.movieName.toString()

        mCinemaID = mTicketForDatabase.ticketCheckout?.cinemaId!!
        mMovieID = mTicketForDatabase.ticketCheckout.movieId.toString()

        mCinemaName = getCinemaName(mCinemaID)
        mCinemaStartTime = mTicketForDatabase.ticketCheckout.timeslot?.startTime.toString()

        mTicketID = mTicketForDatabase.id.toString()




        startActivity(
            CheckOutActivity.newIntent(
                requireContext(), true,
                selectedFoodName = mSelectedFood as ArrayList<String>,
                selectedFoodPrice = mSelectedFoodPrice as ArrayList<String>,
                selectedSeatNameList = mSelectedSeatName,
                mTicketID,
                "",
                mMovieID,mCinemaName,
                mCinemaStartTime,
                mCheckoutBodySnack

            )
        )


    }

    private fun getCinemaName(id: Int): String {
        return if (id == 1) {
            "Mingalar"
        } else if (id == 2) {
            "JCTV"
        } else {
            "MegaTV"
        }
    }


}