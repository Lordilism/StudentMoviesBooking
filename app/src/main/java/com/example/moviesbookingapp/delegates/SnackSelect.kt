package com.example.moviesbookingapp.delegates

import com.example.moviesbookingapp.data.vos.SnackVO

interface SnackSelect {
    fun onTapAdd(snackVO: SnackVO,isAddOrNot: Boolean)
}