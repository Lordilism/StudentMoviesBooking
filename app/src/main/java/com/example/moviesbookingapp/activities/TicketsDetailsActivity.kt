package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.annotation.ColorRes
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_tickets_details.*

class TicketsDetailsActivity : AppCompatActivity() {
    private lateinit var mCinemaInfoVO: CinemaInfoVO
    private var nameString = mutableListOf<String>()
    companion object{
        const val IE_CINEMA_INFO = "IE_CINEMA_INFO"
        fun newIntent(context: Context, mCinemaInfoVO: CinemaInfoVO): Intent {
            val intent =  Intent(context,TicketsDetailsActivity::class.java)
            intent.putExtra(IE_CINEMA_INFO,mCinemaInfoVO)
            return intent

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_details)

        mCinemaInfoVO = intent.getSerializableExtra(IE_CINEMA_INFO) as CinemaInfoVO

        val cinemaName = mCinemaInfoVO.name
        val address = mCinemaInfoVO.address
        tvTitleMoviesFromCinemaDetails.text = cinemaName
        tvLocationOfCinemaDetails.text = address

        mCinemaInfoVO.facilities?.let {
            for (name in it){
                name.title?.let { title -> nameString.add(title) }
            }
        }
        for (i in mCinemaInfoVO.safety!!){
            val chip = Chip(this)
            chip.text = i
//            chip.minHeight = 24
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chip.height = 16
            chipGroupFromDetails.addView(chip)
        }

        tvParking.text = nameString[0]
        tvOnlineFood.text = nameString[1]
        tvWheelChair.text = nameString[2]
        tvTicketCancel.text = nameString[3]


        setUpCinemaDetailsVideo()

    }

    override fun onStart() {
        playerViewCinemaDetails.stopPlayback()
        super.onStart()
    }

    private fun setUpCinemaDetailsVideo() {
        val mediaController = MediaController(this)
//        playerViewCinemaDetails.setMediaController(mediaController)
//        mediaController.setAnchorView(playerViewCinemaDetails)
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.videoplayback}")
        playerViewCinemaDetails.setVideoURI(uri)
        playerViewCinemaDetails.requestFocus()

        playBtnCinemaDetails.setOnClickListener {
            playBtnCinemaDetails.visibility = View.GONE
            playerViewCinemaDetails.start()
            playerViewCinemaDetails.canPause()
        }




    }
}