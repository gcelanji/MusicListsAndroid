package com.example.assignment_2

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_2.view.FragmentClassic
import com.example.assignment_2.view.FragmentPop
import com.example.assignment_2.view.FragmentRock

class MainActivity : AppCompatActivity() {
    private lateinit var rockBtn: ImageView
    private lateinit var classicBtn: ImageView
    private lateinit var popBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentRock())
            .commit()

        rockBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentRock())
                .commit()
        }

        classicBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentClassic())
                .commit()
        }

        popBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentPop())
                .commit()
        }


    }

    private fun initViews() {
        rockBtn = findViewById(R.id.rock_icon)
        classicBtn = findViewById(R.id.classic_icon)
        popBtn = findViewById(R.id.pop_icon)
    }

}