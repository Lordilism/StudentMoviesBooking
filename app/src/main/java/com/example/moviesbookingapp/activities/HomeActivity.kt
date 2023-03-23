package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.AdapterBanner
import com.example.moviesbookingapp.fragments.CinemasFragment
import com.example.moviesbookingapp.fragments.MoviesFragment
import com.example.moviesbookingapp.fragments.ProfileFragment
import com.example.moviesbookingapp.fragments.TicketsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_movies.*

class HomeActivity : AppCompatActivity() {
    lateinit var mBannerAdapter: AdapterBanner
//    lateinit var mNowShowingCommingSoonAdapter: NowShowingCommingSoonAdapter
//    lateinit var mBottomNavWithViewPagerAdapter: BottomNavWithViewPagerAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        setUpBottomNavigationWithViewPager()
        val citiesName = intent.getStringExtra("CITY_NAME").toString()
        tvCitiesName.text = citiesName
        //
        setDefaultFragment()
        //toolbar Listeners
        setUpListenersToolbar()
        //
        setUpBottomNavigation()


    }

    private fun setUpListenersToolbar() {
        toolbarFromHome.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener,
            androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.actionSearch -> {
                        when (bottomNavigation.selectedItemId) {
                            R.id.actionMovies -> {
                                if (tabLayoutNsCs.selectedTabPosition == 0) {
                                    startActivity(
                                        SearchActivity.newIntent(this@HomeActivity, true)
                                    )
                                } else {
                                    startActivity(
                                        SearchActivity.newIntent(
                                            this@HomeActivity,
                                            false
                                        )
                                    )
                                }
                            }
                            R.id.actionCinema -> {
                                startActivity(SearchActivity.newIntent1(this@HomeActivity, true))
                            }
                            R.id.actionProfile -> {


                            }
                        }

                    }
                }
                return false
            }


        })

    }

    // Set Up Default Fragment onCreate
    private fun setDefaultFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, MoviesFragment())
            .commit()
    }

    //Set Up Bottom Navigation View
    private fun setUpBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.actionMovies -> supportFragmentManager.commit {
                    replace(R.id.flContainer, MoviesFragment())
                }
                R.id.actionCinema -> supportFragmentManager.commit {
                    replace(R.id.flContainer, CinemasFragment())
                }
                R.id.actionTickets -> supportFragmentManager.commit {
                    replace(R.id.flContainer, TicketsFragment())
                }
                R.id.actionProfile -> supportFragmentManager.commit {
                    replace(R.id.flContainer, ProfileFragment())
                }

            }
            true
        }
    }


}



