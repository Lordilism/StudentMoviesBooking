package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class MovieVO(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("genres")
    val genres: List<String>?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("overview")
    val overView: String?,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("runtime")
    val runTime: Int?,

    @SerializedName("casts")
    val casts: List<CastersVO>?,

    var type: String


) {
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