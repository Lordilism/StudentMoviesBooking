package com.example.moviesbookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesbookingapp.persistence.typeconverters.TicketForDatabaseTypeConverter
import java.io.Serializable

@Entity(tableName = "ticket")
@TypeConverters(
    TicketForDatabaseTypeConverter::class
)

data class TicketForDatabase(
    @ColumnInfo(name = "ticket_checkout")
    val ticketCheckout:TicketCheckOutVO?,

    @ColumnInfo(name = "address")
    val address:String?,

    @ColumnInfo(name = "movie_name")
    val movieName:String?,

    @ColumnInfo(name = "movie_poster")
    val moviePoster:String?
) :Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}