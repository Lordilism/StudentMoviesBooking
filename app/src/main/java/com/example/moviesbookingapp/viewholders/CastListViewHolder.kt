package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.CastersVO
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_cast_list.view.*

class CastListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(Caster: CastersVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${Caster.profilePath}")
            .into(itemView.ivCircleCast)

        itemView.tvCastName.text = Caster.originalName
    }

}