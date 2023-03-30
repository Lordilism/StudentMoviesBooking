package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.CitiesAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.delegates.CitiesDelegate
import kotlinx.android.synthetic.main.activity_pick_location.*

class PickLocationActivity : AppCompatActivity(),CitiesDelegate {
    private var mMovieModel:MovieModel = MovieModelImpl
    private lateinit var mCitiesAdapter: CitiesAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PickLocationActivity::class.java)

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_location)

        setUpListCities()
        requestData()    //Network

    }

    //Cities from Database
    private fun requestData() {
        mMovieModel.getCities()?.let {
            mCitiesAdapter.setNewData(it)
        }
    }

    private fun setUpListCities() {
        mCitiesAdapter = CitiesAdapter(this)
        rvCities.adapter = mCitiesAdapter
        rvCities.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onTapCities() {
        startActivity(HomeActivity.newIntent(this))
    }

}