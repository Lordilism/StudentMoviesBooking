package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class BannerVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("is_active")
    val isActive: Int?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
) {
}