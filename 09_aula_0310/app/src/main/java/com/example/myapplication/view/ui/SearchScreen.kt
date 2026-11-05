package com.example.myapplication.view.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.FragmentSearch
import com.example.myapplication.R

class SearchScreen : AppCompatActivity() {
    private lateinit val binding: ActivitySearchScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setup()
    }

    fun setup() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<FragmentSearch>(R.id.fragment_container_view)
        }
    }

    fun setBinding() {

    }
}