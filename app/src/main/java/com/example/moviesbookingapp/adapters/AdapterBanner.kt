package com.example.moviesbookingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.BannerVO
import com.example.moviesbookingapp.fragments.MoviesFragment
import com.example.moviesbookingapp.utils.BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class AdapterBanner(private val fragment: MoviesFragment): PagerAdapter() {

    private var bannerList: List<BannerVO> = listOf()
    override fun getCount(): Int {
        return bannerList.count()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val banner = bannerList[position].url
        val view =LayoutInflater.from(fragment.context).inflate(R.layout.view_item_banner,container,false)
        Glide.with(view)
            .load(banner)
            .into(view.ivBannerKFC)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)

    }

    fun setData(BannerData: List<BannerVO>){
        bannerList = BannerData
        notifyDataSetChanged()


    }
}