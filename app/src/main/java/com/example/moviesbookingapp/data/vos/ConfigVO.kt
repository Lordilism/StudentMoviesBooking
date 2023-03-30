package com.example.moviesbookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesbookingapp.persistence.typeconverters.ValueTypeConverter
import com.google.gson.annotations.SerializedName
@Entity(tableName = "config")
@TypeConverters(
    ValueTypeConverter::class
)
data class ConfigVO(
    @SerializedName("id")
    @PrimaryKey
    val id:Int?,

    @SerializedName("key")
    @ColumnInfo(name = "key")
    val key:String?,

    @SerializedName("value")
    @ColumnInfo(name = "value")
    val value:Any?
) {
}