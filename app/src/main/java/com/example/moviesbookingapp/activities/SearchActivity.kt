package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.CinemaDetailsAdapter
import com.example.moviesbookingapp.adapters.NowShowingAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.delegates.MoviesDelegate
import com.example.moviesbookingapp.dummy.facilitiesList
import com.example.moviesbookingapp.dummy.formatList
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), MoviesDelegate, DateDelegate {
    private var mMovieModel: MovieModel = MovieModelImpl
    private var mNowPlayingMovies = mutableListOf<MovieVO>()
    private var mUserSearchMovies = mutableListOf<MovieVO>()
    private var mCommingSoonMovies = mutableListOf<MovieVO>()

    companion object {
        const val IE_FROM_CINEMA = "IE_FROM_CINEMA"
        fun newIntent(context: Context, isNow: Boolean): Intent {
            return Intent(context, SearchActivity::class.java).putExtra("Flag", isNow)
        }

        fun newIntent1(context: Context, isFromCinema: Boolean): Intent {
            return Intent(context, SearchActivity::class.java).putExtra(
                IE_FROM_CINEMA,
                isFromCinema
            )
        }

    }

    lateinit var mNowShowingAdapter: NowShowingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val isFromCinema = intent.getBooleanExtra(IE_FROM_CINEMA, false)
        val isNowShowing = intent.getBooleanExtra("Flag", false)



        setUpListeners(isFromCinema)
        setupSearchRecyclerView(isNowShowing)

        if (isNowShowing) {

            mNowPlayingMovies = mMovieModel.getNowPlaingMoviesFromDatabase() as MutableList<MovieVO>
            searchMovies(mNowPlayingMovies, true)
//            mNowShowingAdapter.setNewData(mUserSearchMovies)

        } else {
            mCommingSoonMovies =
                mMovieModel.getComingSoonMoviesFromDatabase() as MutableList<MovieVO>
            searchMovies(mCommingSoonMovies, false)
//            mNowShowingAdapter.setNewData(mUserSearchMovies)

        }

        setUpSpinner()


    }

    private fun searchMovies(nowPlayingMovie: List<MovieVO>?, flag: Boolean) {
        etSearchMovies.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mUserSearchMovies.clear()
                val userInput = s.toString()
                if (userInput.isNotEmpty()) {
                    for (data in nowPlayingMovie!!) {
                        data.let {
                            if (it.originalTitle.startsWith(userInput, true)) {
                                mUserSearchMovies.add(it)
                            }
                        }
                    }
                    mNowShowingAdapter.setNewData(mUserSearchMovies)
                }else{
                    mUserSearchMovies = mutableListOf()
                }


            }
        })

//        mUserSearchMovies.size

    }


    private fun setUpSpinner() {
        val demoFacilities = ArrayAdapter(
            applicationContext, android.R.layout.simple_spinner_item,
            facilitiesList
        )
        demoFacilities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFacilities.adapter = demoFacilities

        val demoFormat = ArrayAdapter(
            applicationContext, android.R.layout.simple_spinner_item,
            formatList
        )
        demoFormat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFormat.adapter = demoFormat
    }

    private fun setUpListeners(isFromCinema: Boolean) {
        when (isFromCinema) {
            true -> {
                llFromCinemaSearch.visibility = View.VISIBLE
                tvPriceRange.visibility = View.VISIBLE
                sliderPrice.visibility = View.VISIBLE
                tvShowTime.visibility = View.VISIBLE
                sliderTime.visibility = View.VISIBLE
                setUpRecyclerViewCinema()
            }
            false -> {

            }
        }

    }

    private fun setUpRecyclerViewCinema() {
        rvFromCinemaSearch.adapter = CinemaDetailsAdapter(false, false, this, this)
        rvFromCinemaSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupSearchRecyclerView(isNowShowing: Boolean) {
//        val flag = intent.getBooleanExtra("Flag", true)
        mNowShowingAdapter = NowShowingAdapter(isNowShowing, this)
        rvSearch.adapter = mNowShowingAdapter
        rvSearch.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }


    override fun onTapMovies(movieId: String) {

    }


    override fun onTapDate(
        cinemaTimeSlotId: Int,
        bookingDate: String,
        startTime: String?,
        cinemaName: String?
    ) {

    }

    override fun onTapDetails(mCinemaInfoVO: CinemaInfoVO) {
        startActivity(TicketsDetailsActivity.newIntent(this, mCinemaInfoVO))
    }
}



