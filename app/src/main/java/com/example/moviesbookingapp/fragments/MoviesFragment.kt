package com.example.moviesbookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.AdapterBanner
import com.example.moviesbookingapp.adapters.NSCSAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*


class MoviesFragment : Fragment() {

    private var mMovieModel:MovieModel = MovieModelImpl
    lateinit var mBanneradapter: AdapterBanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpBanner(view)
        setUpNowShowing(view)

        requestData()



        super.onViewCreated(view, savedInstanceState)

    }

    private fun requestData() {

        mMovieModel.getBanner(
            onSuccess = {

                mBanneradapter.setData(it)
            },
            onFailure = {
                Toast.makeText(this.context,"Fail to fetch Banner",Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpNowShowing(view: View) {
        val nscsAdapter = NSCSAdapter(this,mMovieModel )
        view.viewpagerNowShowing.adapter = nscsAdapter

        TabLayoutMediator(view.tabLayoutNsCs, viewpagerNowShowing) { tab, position ->
            when (position) {

                0 -> tab.text = "Now Showing"
                else -> tab.text = "Coming Soon"

            }
        }.attach()
    }

    private fun setUpBanner(view: View) {
        mBanneradapter = AdapterBanner((this))
        view.viewPagerBanner.adapter = mBanneradapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }


}