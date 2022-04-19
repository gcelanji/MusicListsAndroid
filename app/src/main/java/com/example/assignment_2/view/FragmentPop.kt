package com.example.assignment_2.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.assignment_2.R
import com.example.assignment_2.model.NetworkResponse
import com.example.assignment_2.model.TrackItem
import com.example.assignment_2.model.remote.DataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "FragmentPop"

class FragmentPop : Fragment() {
    private lateinit var songsResponse: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var swipeToRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.fragment_layout,
            container,
            false
        )
        initViews(view)
        getData()

        return view
    }

    override fun onResume() {
        super.onResume()
        swipeToRefresh.setOnRefreshListener {
            getData()
            swipeToRefresh.isRefreshing = false
        }
    }


    private fun initViews(view: View) {
        songsResponse = view.findViewById(R.id.rock_list)
        songsResponse.layoutManager = LinearLayoutManager(context)
        swipeToRefresh = view.findViewById(R.id.swipeRefreshLayout)
    }

    private fun getData() {
        DataService.initRetrofit().fetchData(
            "pop",
            "music",
            "song",
            50
        ).enqueue(
            object : Callback<NetworkResponse> {
                override fun onResponse(
                    call: Call<NetworkResponse>,
                    response: Response<NetworkResponse>
                ) {
                    if (response.isSuccessful) {

                        Log.d(TAG, "onResponse: ${response.body()}")
                        updateAdapter(response.body())
                    } else {
                        showError()
                    }
                }

                override fun onFailure(call: Call<NetworkResponse>, t: Throwable) {

                }


            }
        )
    }

    private fun updateAdapter(body: NetworkResponse?) {
        Log.d(TAG, "updateAdapter: ${body?.resultCount}")
        body?.let {
            Log.d(TAG, "updateAdapterBody: In here")
            //val testTrack = TrackItem("ERT", "ERT","SGSGD",1.2F,"ASF")
            //val testList = arrayListOf<TrackItem>(testTrack, testTrack, testTrack)
            adapter = DataAdapter(it.results) { item -> playSound(item) }
            songsResponse.adapter = adapter
            showToast(adapter.itemCount)
        } ?: showError()
    }

    private fun showError() {
        Log.d(TAG, "showError: Error fetching the data")
    }

    private fun playSound(item: TrackItem) {
        playContentUri(item.previewUrl)
    }

    private fun playContentUri(audioUrl: String) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(audioUrl)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }


    private fun showToast(items: Int) {
        // triggers an java.lang.NullPointerException when switching to landscape mode
        Toast.makeText(context, "Found $items Results.", Toast.LENGTH_SHORT).show()
    }

}