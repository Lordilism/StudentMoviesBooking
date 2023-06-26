package com.example.moviesbookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesbookingapp.persistence.typeconverters.CasterVoListTypeConverter
import com.example.moviesbookingapp.persistence.typeconverters.GenreListTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
@Entity(tableName = "movie")
@TypeConverters(
    GenreListTypeConverter::class,
    CasterVoListTypeConverter::class
)
data class MovieVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Long?,

    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    val originalTitle: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<String>?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overView: String?,

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    val rating: Double?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runTime: Int?,

    @SerializedName("casts")
    @ColumnInfo(name= "casts")
    val casts: List<CastersVO>?,

    @ColumnInfo(name = "type")
    var type: String?




):Serializable {
    fun basetenRating(): String {
        return "%.2f".format(rating)
    }

    fun formatDate(Date: String, forDetails: Boolean): String {
        val responseDate = Date

        val date = LocalDate.parse(responseDate)
        val dayonMonth = date.dayOfMonth
        val monthString = date.month.toString()
        var str = String()
        for (index in 0..2) {
            str += monthString[index]
        }

        val suffixes =
            mapOf(1 to "st", 2 to "nd", 3 to "rd", 21 to "st", 22 to "nd", 23 to "rd", 31 to "st")
        val default = suffixes.getOrDefault(dayonMonth, "th")

        val releaseYr = date.year.toString()
        val datetoView = "${dayonMonth} ${default}\n${str}"

        val releaseDateForDetails = "${str} ${dayonMonth}${default},\n${releaseYr}"

        if (forDetails) {
            return releaseDateForDetails
        } else return datetoView


    }

    fun getDurationInHour(): String? {
        val inHour= runTime?.let {
           "${it/60}hr ${it%60}min"
        }
        return inHour
    }
}

const val NOW_PLAYING = "NOW_PLAYING"
const val COMING_SOON = "COMING_SOON"