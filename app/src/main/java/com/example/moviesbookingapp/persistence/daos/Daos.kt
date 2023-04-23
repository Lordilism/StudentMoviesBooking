package com.example.moviesbookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.OtpResponse

@Dao
interface Daos {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInformation(otp: OtpResponse?)

    @Query("SELECT * FROM otp WHERE code = :code")
    fun getInformation(code:Int):OtpResponse

    @Query("SELECT * FROM city")
    fun getCities():List<CityVo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities:List<CityVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfig(config: List<ConfigVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieVO>)

    @Query("SELECT * FROM movie")
    fun getAllMovies():List<MovieVO>

    @Query("SELECT * FROM movie WHERE type = :type")
    fun getMoviesByType(type:String):List<MovieVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanners(banners: List<BannerVO>)

    @Query("SELECT * FROM banner")
    fun getBanner():List<BannerVO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovies(movie: MovieVO)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMoviesById(movieId: Int):MovieVO?

    @Query("SELECT * FROM config WHERE `key` = :key")
    fun getConfig(key:String):ConfigVO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicket(ticket: TicketForDatabase)

    @Query("SELECT * FROM ticket")
    fun getAllTickets():List<TicketForDatabase>




}