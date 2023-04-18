package com.example.moviesbookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.adapters.FoodAdapter
import com.example.moviesbookingapp.data.models.MovieModel
import com.example.moviesbookingapp.data.models.MovieModelImpl
import com.example.moviesbookingapp.data.vos.SnackCategoryVO
import com.example.moviesbookingapp.data.vos.SnackVO
import com.example.moviesbookingapp.delegates.SnackSelect
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_grab_bite.*

class GrabBiteActivity : AppCompatActivity(), SnackSelect {
    lateinit var mFoodAdapter: FoodAdapter
    private var mMovieModel: MovieModel = MovieModelImpl

    private var mFood: MutableList<SnackVO> = mutableListOf()

    private var mPriceList: MutableList<Int> = mutableListOf()

//    private var mSnackCategory:List<SnackCategoryVO> = listOf()

    private var mSnackCategory: MutableList<SnackCategoryVO>? = null

    private var mFoodListForPrice: ArrayList<String> = ArrayList()
    private var mSelectedFoodList = ArrayList<String>()
    private var mSelectedSeatList:MutableList<String> = mutableListOf()

    private lateinit var mTime:String
    private lateinit var mMovieName: String

    private lateinit var mMovieID: String

    companion object {
        const val IE_SEAT_NAME_LIST = "IE_SEAT_NAME_LIST"
        const val IE_MOVIE_NAME = "IE_MOVIE_NAME"
        const val IE_TIME = "IE_TIME"
        const val IE_ID_FOR_CALL = "ID_FOR_CALL"
        fun newIntent(
            context: Context,
            seatNames: MutableList<String>,
            movieNameForCheckout: String,
            time: String,
            mMovieID: String
        ): Intent {
            val intent = Intent(context, GrabBiteActivity::class.java)
            intent.putStringArrayListExtra(
                IE_SEAT_NAME_LIST,
                seatNames as java.util.ArrayList<String>
            )

            intent.putExtra(IE_MOVIE_NAME,movieNameForCheckout)
            intent.putExtra(IE_TIME,time)
            intent.putExtra(IE_ID_FOR_CALL,mMovieID)

            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grab_bite)

//        setUpFoodTab(it)
        mSelectedSeatList = intent.getStringArrayListExtra(IE_SEAT_NAME_LIST)!!

        mTime = intent.getStringExtra(IE_TIME).toString()
        Log.i("time",mTime)

        mMovieName = intent.getStringExtra(IE_MOVIE_NAME).toString()
        Log.i("Name", mMovieName)

        mMovieID = intent.getStringExtra(IE_ID_FOR_CALL).toString()


        setUpSheet()
        setUpGrabFoodRecyclerView()

        requestData()


        setUpTabListeners()
        SetupListeners()




    }

    private fun setUpTabListeners() {
        tabLayoutGrabBite.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    requestFood(0)
                } else {
                    tab?.position?.let {
                        mSnackCategory?.get(it)?.id?.let { id ->
                            requestFood(id)
                        }
                    }
                }

            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun requestFood(id: Int) {
        mMovieModel.getSnack(
            "Bearer ${mMovieModel.getOtp(201)?.token}",
            id,
            onSuccess = {
                mFoodAdapter.bindData(it)
            },
            onFailure = {

            }

        )
    }

    private fun requestData() {
        mMovieModel.getSnackCategory(
            "Bearer ${mMovieModel.getOtp(201)?.token}",
            onSuccess = {

                setUpFoodTab(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, "Fail", Snackbar.LENGTH_INDEFINITE).show()
            }
        )


    }

    private fun setUpFoodTab(snackCategoryVO: List<SnackCategoryVO>) {
        mSnackCategory = snackCategoryVO as MutableList<SnackCategoryVO>
        mSnackCategory!!.add(0, SnackCategoryVO(0, "All", "All", null, null, null, null))
        mSnackCategory!!.forEach {
            tabLayoutGrabBite.newTab().apply {
                text = it.title
                tabLayoutGrabBite.addTab(this)
            }
        }
    }

    private fun setUpGrabFoodRecyclerView() {
        mFoodAdapter = FoodAdapter(this)
        rvGrabFood.adapter = mFoodAdapter
        rvGrabFood.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)


    }

    private fun SetupListeners() {

        tvSkip.setOnClickListener {
            startActivity(
                CheckOutActivity.newIntent(
                    this, isAbleToCancel = false,
                    mSelectedFoodList,
                    mFoodListForPrice,
                    mSelectedSeatList,
                    mTime,
                    mMovieName,
                    mMovieID


                )
            )
            finish()
        }

//        mFood.forEach {
//            mSelectedFoodList.add(it.name)
//        }

        ivForward.setOnClickListener {
            startActivity(
                CheckOutActivity.newIntent(
                    this, false,
                    mSelectedFoodList,
                    mFoodListForPrice,
                    mSelectedSeatList,
                    mTime,
                    mMovieName,
                    mMovieID

                )
            )
            finish()
        }

    }

//    private fun sendFoodName(mFood: MutableList<SnackVO>): java.util.ArrayList<String> {
//        this.mFood.forEach {
//            mSelectedFoodList.add(it.name)
//        }
//        return mSelectedFoodList
//    }

    private fun setUpSheet() {
        val sheet = BottomSheetBehavior.from(bottomSheet)
        sheet.peekHeight = 0
        sheet.isHideable = true

        bottomSheetIndicator.setOnClickListener {
            when {
                sheet.state != BottomSheetBehavior.STATE_EXPANDED -> {
                    sheet.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomSheetIndicator.rotation = 180F

                }
                else -> {
                    sheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    bottomSheetIndicator.rotation = 0F
                }

            }

        }
    }


    override fun onTapAdd(snackVO: SnackVO, isAddOrNot: Boolean) {

        when (isAddOrNot) {
            true -> {
                var value = 0
                mFood.add(value, snackVO)

                mSelectedFoodList.add(mFood[value].name)
                mFoodListForPrice.add(mFood[value].price.toString())
                value = value.inc()

//                mFood.forEach {
//                    mSelectedFoodList.add(it.name)
//                }

                mPriceList.add(snackVO.price)
                tvFoodTotalPrice.text = "${mPriceList.sum()} Ks"
            }
            false -> {
                mPriceList.removeLastOrNull()
                mFood.removeLastOrNull()
                tvFoodTotalPrice.text = "${mPriceList.sum()} Ks"
            }

        }


    }
}