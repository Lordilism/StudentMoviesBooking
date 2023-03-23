package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.example.moviesbookingapp.R
import kotlinx.android.synthetic.main.activity_tickets_details.*

class TicketsDetailsActivity : AppCompatActivity() {
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,TicketsDetailsActivity::class.java)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_details)

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