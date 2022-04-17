package com.example.assignment_2.model.remote

import com.example.assignment_2.model.NetworkResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//const val rock_URL = "https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
//const val classic_URL = "https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
//const val pop_URL = "https://itunes.apple.com/search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"

interface DataService {
    @GET(END_POINT)
    fun fetchData(
        @Query(PARAM_TERM)  term : String,
        @Query(PARAM_MEDIA)  media : String,
        @Query(PARAM_ENTITY)  entity : String,
        @Query(PARAM_LIMIT)  limit : Int
    ): Call<NetworkResponse>

    companion object{
        private const val BASE_URL = "https://itunes.apple.com/"
        private const val END_POINT = "search"
        private const val PARAM_TERM = "term"
        private const val PARAM_MEDIA = "amp;media"
        private const val PARAM_ENTITY = "amp;entity"
        private const val PARAM_LIMIT = "amp;limit"



        fun initRetrofit() : DataService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataService::class.java)
        }
    }

}