package com.example.moviesbookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.activities.MoviesDetailsActivity
import com.example.moviesbookingapp.adapters.NowShowingAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.delegates.MoviesDelegate
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_now_showing.view.*


class NowShowingFragment(val mMovieModel: MovieModel) : Fragment(), MoviesDelegate {

//    private var mMovieModel: MovieModel = MovieModelImpl
    lateinit var mNowShowingAdapter: NowShowingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_showing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpNowShowingRecyclerView(view)

        requestData()


    }

    private fun requestData() {


            mMovieModel.getNowPlaingMovies(
                onSuccess = {
                    mNowShowingAdapter.setNewData(it)
                },
                onFailure = {
                    Toast.makeText(this.context, "Hello!No Internet", Toast.LENGTH_SHORT).show()
                }
            )

    }


    private fun setUpNowShowingRecyclerView(view: View) {
        mNowShowingAdapter = NowShowingAdapter(true, this,)
        view.rvMoviesFromNowShowing.adapter = mNowShowingAdapter

        view.rvMoviesFromNowShowing.layoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
    }


    override fun onTapMovies(name: String) {
//        Toast.makeText(this.context,name, Toast.LENGTH_LONG).show()

        this.context?.let {
            startActivity(MoviesDetailsActivity.newIntent(it,name,true))
        }

//        this.view?.let { Snackbar.make(it,name,Snackbar.LENGTH_INDEFINITE).show() }
    }


}