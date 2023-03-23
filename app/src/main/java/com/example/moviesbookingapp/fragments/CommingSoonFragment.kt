package com.example.moviesbookingapp.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.activities.MoviesDetailsActivity
import com.example.moviesbookingapp.adapters.NowShowingAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.delegates.MoviesDelegate
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_comming_soon.view.*

class CommingSoonFragment(private val model: MovieModel) : Fragment(), MoviesDelegate {
    lateinit var mComingAdapter: NowShowingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_comming_soon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerComingSoon(view)

        requestData()

    }

    private fun requestData() {
        model.getComingSoon(
            onSuccess = {
                mComingAdapter.setNewData(it)
            },
            onFailure = {
                Toast.makeText(this.context, "Fail to fetch data!", Toast.LENGTH_SHORT).show()
            },

            )
    }

    private fun setUpRecyclerComingSoon(view: View) {
        mComingAdapter = NowShowingAdapter(false, this)
        view.rvMoviesFromComingSoon.adapter = mComingAdapter

        view.rvMoviesFromComingSoon.layoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
    }


    override fun onTapMovies(movieId: String) {
//        Toast.makeText(this.context,movieId,Toast.LENGTH_SHORT).show()
//        this.view?.let { Snackbar.make(it,movieId,Snackbar.LENGTH_INDEFINITE) }
        this.context?.let {
            startActivity(MoviesDetailsActivity.newIntent(it,movieId,false))
        }


    }


}