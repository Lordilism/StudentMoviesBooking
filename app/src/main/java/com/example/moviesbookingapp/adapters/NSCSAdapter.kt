package com.example.moviesbookingapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.fragments.CommingSoonFragment
import com.example.moviesbookingapp.fragments.NowShowingFragment

class NSCSAdapter(fragment: Fragment, val mMovieModel: MovieModel) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
         when(position){
            0->{
                val nowShowingFragment = NowShowingFragment(mMovieModel)
                return nowShowingFragment
            }
            else->{
                return CommingSoonFragment(mMovieModel)
            }
        }



    }
}