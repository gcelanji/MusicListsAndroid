package com.example.assignment_2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class NetworkResponse(
    val resultCount: Int,
    val results : ArrayList<TrackItem>
)

data class TrackItem(
    val artistName : String,
    val collectionName : String,
    val image : String,
    val trackPrice : Float,
    val previewSong : String
)
