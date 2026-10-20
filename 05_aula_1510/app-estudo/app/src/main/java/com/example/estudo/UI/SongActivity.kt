package com.example.estudo.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.estudo.R
import com.example.estudo.databinding.SearchPageBinding
import com.example.estudo.databinding.SongPageBinding
import com.example.estudo.viewModel.MainViewModel

class SongActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : SongPageBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SongPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListeners()

        mainVM.song_setup()
    }

    fun setListeners() {
        binding.imgBack.setOnClickListener(this)
    }

    fun setObserver() {
        mainVM.getTittleText().observe(this, Observer {
            binding.textSearchResult.text = it
        })

        mainVM.getBackSearch().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SearchActivity::class.java))
                finish()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.img_back -> mainVM.backSearch()
        }
    }
}