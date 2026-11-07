package com.example.myapplication.view.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchScreenBinding
import com.example.myapplication.view.fragments.FragmentFavorite
import com.example.myapplication.view.fragments.FragmentList
import com.example.myapplication.view.fragments.FragmentSearch

class MainScreen : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySearchScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setup()
        setListener()
        setBinding()
    }

    fun clearColor() {
        binding.imgSearch.clearColorFilter()
        binding.imgFavorite.clearColorFilter()
        binding.imgList.clearColorFilter()
    }

    fun setup() {
        binding.imgSearch.clearColorFilter()
        binding.imgSearch.setColorFilter(getColor(R.color.white))
    }

    fun setListener() {
        binding.imgSearch.setOnClickListener(this)
        binding.imgFavorite.setOnClickListener(this)
        binding.imgList.setOnClickListener(this)
    }

    fun setBinding() {

    }

    override fun onClick(view: View) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val whiteColor = getColor(R.color.white)
        clearColor()

        when (view.id) {
            R.id.img_search -> {
                navController.navigate(R.id.fragmentSearch)
                binding.imgSearch.setColorFilter(whiteColor)
            }
            R.id.img_favorite -> {
                navController.navigate(R.id.fragmentFavorite)
                binding.imgFavorite.setColorFilter(whiteColor)
            }
            R.id.img_list -> {
                navController.navigate(R.id.fragmentList)
                binding.imgList.setColorFilter(whiteColor)
            }
        }
    }
}