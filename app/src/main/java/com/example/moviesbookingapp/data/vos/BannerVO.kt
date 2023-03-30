package com.example.moviesbookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "banner")
data class BannerVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String?,

    @SerializedName("is_active")
    @ColumnInfo(name = "is_active")
    val isActive: Int?,

    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    val updatedAt: String?
) {
}