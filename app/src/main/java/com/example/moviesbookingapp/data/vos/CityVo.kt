package com.example.moviesbookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "city")

data class CityVo(
    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("name")
    @ColumnInfo(name="name")
    var name:String?,

    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    val createdAt:String?,

    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    val updatedAt:String?
) {
}