package com.example.moviesbookingapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.activities.HomeActivity
import com.example.moviesbookingapp.activities.LogInActivity
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    //    lateinit var mProfileAdapter: ProfileAdapter
    private val mMovieModel: MovieModel = MovieModelImpl
    private lateinit var fragmentContext: Context
    private lateinit var manager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        mProfileAdapter = ProfileAdapter()
//        view.rvFromProfile.adapter = mProfileAdapter
//        view.rvFromProfile.layoutManager=
//            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
        rvSettingLogOut.setOnClickListener {

            val dialog = AlertDialog.Builder(requireContext(), R.style.CustomDialogTheme)
                .setTitle("Confirm Logout")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("Yes") { dialog, _ ->
                    mMovieModel.logOut("Bearer ${mMovieModel.getOtp(201)?.token}",
                        onSuccess = {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            (activity as HomeActivity).finish()

                            Intent(requireActivity(), LogInActivity::class.java).also {
                                startActivity(it)
                            }


                        }, onFailure = {

                        })
                }

                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialog.show()


        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


}