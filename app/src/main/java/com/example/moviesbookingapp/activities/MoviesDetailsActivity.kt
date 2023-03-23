package com.example.moviesbookingapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.CastListAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_movies_details.*

class MoviesDetailsActivity : AppCompatActivity() {
    lateinit var mCastListAdapter: CastListAdapter
    private var mMovieModel: MovieModel = MovieModelImpl



    companion object {
        const val IE_MOVIE_ID = "IE_MOVIE_ID"
        const val IE_NOW_SHOWING = "IE_NOW_SHOWING"
        fun newIntent(context: Context, movieId: String, nowShowing:Boolean): Intent {
            val intent = Intent(context,MoviesDetailsActivity::class.java)
            intent.putExtra(IE_MOVIE_ID, movieId)
            intent.putExtra(IE_NOW_SHOWING,nowShowing)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        val nowShowing = intent.getBooleanExtra(IE_NOW_SHOWING,false)

        setVisibilityOfNotify(nowShowing)
        setUpMoviesInfo()
        setUpBackBtn()
        setUpCastRecyclerView()
        navigateToDateSelect()
        setUpVideo()

        val movieId = intent.getStringExtra(IE_MOVIE_ID)

//        Snackbar.make(window.decorView, movieId ?: "", Snackbar.LENGTH_INDEFINITE).show()

        movieId?.let {
            requestData(it)
        }


    }

    private fun requestData(movieId: String) {
        mMovieModel.getMoviesDetails(
            onSuccess = {
                bindData(it)

            },
            onFailure = {

            },
            movieId = movieId
        )
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMoviesImage)

        tvMoviesTitleFromDetails.text = movie.originalTitle
        tvRatingFromDetails.text = movie.basetenRating()
        tvStoryLine.text = "Storyline\n\n${movie.overView}"

        tvMOvieReleaseDate.text =
            "Relaease Date\n${movie.formatDate(movie.releaseDate.toString(), true)}"

        tvMovieDuration.text = "Duration\n${movie.getDurationInHour()}"

        movie.casts?.let {
            mCastListAdapter.setNewData(it)
        }

        bindGenres(movie)


    }

    private fun bindGenres(movie: MovieVO) {
        movie.genres?.let {genreList->
            genreList.count().let {
                when (it) {
                    1->{
                        chipOne.text  = genreList[0]
                        chipTwo.visibility = View.GONE
                        chipThree.visibility = View.GONE
                        chipFour.visibility = View.GONE
                    }

                    2->{
                        chipOne.text = genreList[0]
                        chipTwo.text = genreList[1]
                        chipThree.visibility = View.GONE
                        chipFour.visibility = View.GONE
                    }
                    3->{
                        chipOne.text = genreList[0]
                        chipTwo.text = genreList[1]
                        chipThree.text  = genreList[2]
                        chipFour.visibility = View.GONE
                    }
                    4->{
                        chipOne.text = genreList[0]
                        chipTwo.text = genreList[1]
                        chipThree.text  = genreList[2]
                        chipFour.text = genreList[3]
                    }
                    else->{
                        chipOne.visibility = View.GONE
                        chipTwo.visibility = View.GONE
                        chipThree.visibility = View.GONE
                        chipFour.visibility = View.GONE
                    }



                }
            }


        }

    }

    override fun onRestart() {
        videoTrailor.start()
        super.onRestart()
    }


    private fun navigateToDateSelect() {
        btnBooking.setOnClickListener {
            startActivity(DateSelectActivity.newIntent(this))
        }
    }

    private fun setUpMoviesInfo() {
        val title = intent.getStringExtra("Movies_Name").toString()
        val rating = intent.getStringExtra("Rating_Status").toString()
        tvMoviesTitleFromDetails.text = title
        tvRatingFromDetails.text = rating
    }

    private fun setUpBackBtn() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpCastRecyclerView() {
        mCastListAdapter = CastListAdapter()
        rvCast.adapter = mCastListAdapter
        rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setVisibilityOfNotify(nowShowing: Boolean) {

        when (nowShowing) {
            true -> flNotification.visibility = View.GONE
            else -> {
                flNotification.visibility = View.VISIBLE
                viewGradient.visibility = View.GONE
                btnBooking.visibility = View.GONE
            }
        }
    }

    private fun setUpVideo() {
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.demo_trailor}")
        videoTrailor.apply {
            setVideoURI(uri)
            requestFocus()
            start()
            canPause()
        }
    }
}