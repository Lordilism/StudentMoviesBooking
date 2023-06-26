package com.example.moviesbookingapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import okhttp3.internal.wait

class LogOutDialogFragment(val mContext: Context) : DialogFragment() {
    private var mMovieModel: MovieModel = MovieModelImpl
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(mContext, R.style.CustomDialogTheme)
        builder.setTitle("Confirm Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { dialog, _ ->


                mMovieModel.logOut("Bearer ${mMovieModel.getOtp(201)?.token}",
                    onSuccess = {
                        Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
                    },
                    onFailure = {

                    }
                )

//                mMovieModel.logOut("Bearer ${mMovieModel.getOtp(201)?.token}", onSuccess = {
////                    dialog.dismiss()
//                    Toast.makeText(mContext, it.message, Toast.LENGTH_SHORT).show()
//                    val intent = Intent(mContext, LogInActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    startActivity(intent)
//                },
//                    onFailure = {
//                        Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
//                    })


            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        return builder.create()

    }

}
