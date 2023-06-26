package com.example.moviesbookingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.activities.TicketsDetailsActivity
import com.example.moviesbookingapp.adapters.CinemaDetailsAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.example.moviesbookingapp.delegates.DateDelegate
import kotlinx.android.synthetic.main.fragment_cinemas.view.*


class CinemasFragment : Fragment(), DateDelegate {
    private var mMovieModel:MovieModel = MovieModelImpl
    lateinit var mCinemaDetailsAdapter : CinemaDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinemas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = this.context ?:null
        mCinemaDetailsAdapter = CinemaDetailsAdapter(true,false,this, this.requireActivity().applicationContext)
        view.rvFromCinemaFragment.adapter = mCinemaDetailsAdapter
        view.rvFromCinemaFragment.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        super.onViewCreated(view, savedInstanceState)

        requestData()


    }

    private fun requestData() {
        mMovieModel.getCinemaInfo(onSuccess = {
            mCinemaDetailsAdapter.setNewDataForCinema(it)
        },
        onFailure = {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
    }

    override fun onTapDate(
        cinemaTimeSlotId: Int,
        bookingDate: String,
        startTime: String?,
        cinemaName: String?
    ) {

    }

    override fun onTapDetails(mCinemaInfoVO: CinemaInfoVO) {
        startActivity(this.context?.let { TicketsDetailsActivity.newIntent(it,mCinemaInfoVO) })
    }


}