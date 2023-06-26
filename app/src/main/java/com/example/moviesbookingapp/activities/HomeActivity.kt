package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.AdapterBanner
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
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
    private var mMovieModel: MovieModel = MovieModelImpl
    private lateinit var sharedPreferences: SharedPreferences
    private var mCitieName = "Yangon"

    companion object {
        const val IE_CITY_NAME = "IE_CITY_NAME"
        const val CITIES_NAME = "CITIES_NAME"
        fun newIntent(context: Context, cityName: String): Intent {
            return Intent(context, HomeActivity::class.java).putExtra(IE_CITY_NAME, cityName)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        setUpBottomNavigationWithViewPager()
        val cities = intent.getStringExtra(IE_CITY_NAME)?:"Yangon"
        sharedPreferences = getSharedPreferences(CITIES_NAME, Context.MODE_PRIVATE)

        saveToShare(mCitieName)

        tvCitiesName.text = cities
        //
        setDefaultFragment()
        //toolbar Listeners
        setUpListenersToolbar()
        //
        setUpBottomNavigation()


    }

    private fun saveToShare(mCitieName: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(CITIES_NAME, mCitieName)
        editor.apply()

    }

    override fun onRestart() {
        super.onRestart()
        mCitieName = sharedPreferences.getString(CITIES_NAME, "Yangon").toString()
        tvCitiesName.text = mCitieName
    }

    override fun onPause() {
        super.onPause()
        mCitieName = sharedPreferences.getString(CITIES_NAME,"Yangon").toString()
        tvCitiesName.text = mCitieName
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



