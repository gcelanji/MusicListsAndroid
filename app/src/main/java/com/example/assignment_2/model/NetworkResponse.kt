package com.example.assignment_2.model


data class NetworkResponse(
    val resultCount: Int,
    val results: ArrayList<TrackItem>
)

data class TrackItem(
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val trackPrice: Float,
    val previewUrl: String
)
