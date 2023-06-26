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
import com.example.moviesbookingapp.data.vos.CheckOutBodySnack
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


    private var mChekOutSnack: MutableList<CheckOutBodySnack> = mutableListOf()

//    private var mSnackCategory:List<SnackCategoryVO> = listOf()

    private var mSnackCategory: MutableList<SnackCategoryVO>? = null

    private var mFoodListForPrice: ArrayList<String> = ArrayList()
    private var mSelectedFoodList = ArrayList<String>()
    private var mSelectedSeatList: MutableList<String> = mutableListOf()

    private lateinit var mTimeSlot: String
    private lateinit var mMovieBookingDate: String
    private lateinit var mMovieID: String
    private lateinit var mCinemaName:String
    private lateinit var mCinemaStartTime:String

    companion object {
        const val IE_SEAT_NAME_LIST = "IE_SEAT_NAME_LIST"
        const val IE_MOVIE_BOOKING_DATE = "IE_MOVIE_NAME"
        const val IE_SLOT_TIME = "IE_TIME"
        const val IE_ID_FOR_CALL = "ID_FOR_CALL"
        const val IE_CINEMA_NAME = "IE_CINEMA_NAME"
        const val IE_CINEMA_START_TIME = "IE_CINEMA_START_TIME"
        fun newIntent(
            context: Context,
            seatNames: MutableList<String>,
            bookingDate: String,
            timeSlotID: String,
            mMovieID: String,
            cinemaName: String,
            cinemaStartTime: String
        ): Intent {
            val intent = Intent(context, GrabBiteActivity::class.java)
            intent.putStringArrayListExtra(
                IE_SEAT_NAME_LIST,
                seatNames as java.util.ArrayList<String>
            )

            intent.putExtra(IE_MOVIE_BOOKING_DATE, bookingDate)
            intent.putExtra(IE_SLOT_TIME, timeSlotID)
            intent.putExtra(IE_ID_FOR_CALL, mMovieID)
            intent.putExtra(IE_CINEMA_NAME,cinemaName)
            intent.putExtra(IE_CINEMA_START_TIME,cinemaStartTime)

            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grab_bite)

//        setUpFoodTab(it)
        mSelectedSeatList = intent.getStringArrayListExtra(IE_SEAT_NAME_LIST)!!

        mTimeSlot = intent.getStringExtra(IE_SLOT_TIME).toString()
        Log.i("time", mTimeSlot)

        mMovieBookingDate = intent.getStringExtra(IE_MOVIE_BOOKING_DATE).toString()
        Log.i("Name", mMovieBookingDate)

        mMovieID = intent.getStringExtra(IE_ID_FOR_CALL).toString()
        Log.d("id", mMovieID.toString())

        mCinemaName = intent.getStringExtra(IE_CINEMA_NAME).toString()
        Log.d("cinemaName", mCinemaName.toString())

        mCinemaStartTime = intent.getStringExtra(IE_CINEMA_START_TIME).toString()

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
            for (item in mFood){
                val vo = CheckOutBodySnack(item.id)
                mChekOutSnack.add(vo)
            }

            val itemMap = mChekOutSnack.associateBy { it.id }. toMutableMap()
            for (item in mChekOutSnack){
                if (itemMap.containsKey(it.id)){
                    val existItem = itemMap[it.id]
                    existItem?.quantity = existItem?.quantity?.plus(1)
                }else{
                    itemMap[item.id] = item
                }
            }
            val  updateItem = itemMap.values.toList()
            startActivity(
                CheckOutActivity.newIntent(
                    this, isAbleToCancel = false,
                    mSelectedFoodList,
                    mFoodListForPrice,
                    mSelectedSeatList,
                    mTimeSlot,
                    mMovieBookingDate,
                    mMovieID,
                    mCinemaName,
                    mCinemaStartTime,
                    updateItem


                )
            )
            finish()
        }

//        mFood.forEach {
//            mSelectedFoodList.add(it.name)
//        }

        ivForward.setOnClickListener {
            for (item in mFood){
                val vo = CheckOutBodySnack(item.id)
                mChekOutSnack.add(vo)
            }

             val itemMap = mChekOutSnack.associateBy { it.id }. toMutableMap()
            for (item in mChekOutSnack){
                if (itemMap.containsKey(it.id)){
                    val existItem = itemMap[it.id]
                    existItem?.quantity = existItem?.quantity?.plus(1)
                }else{
                    itemMap[item.id] = item
                }
            }
            val  updateItem = itemMap.values.toList()

            startActivity(
                CheckOutActivity.newIntent(
                    this, false,
                    mSelectedFoodList,
                    mFoodListForPrice,
                    mSelectedSeatList,
                    mTimeSlot,
                    mMovieBookingDate,
                    mMovieID,
                    mCinemaName,
                    mCinemaStartTime,
                    updateItem

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
                value.inc()

//                mFood.forEach {
//                    mSelectedFoodList.add(it.name)
//                }

                mPriceList.add(snackVO.price)
                tvFoodTotalPrice.text = "${mPriceList.sum()} Ks"
            }
            false -> {

                mFood.removeLastOrNull()
                tvFoodTotalPrice.text = "${mPriceList.sum()} Ks"
            }

        }


    }
}