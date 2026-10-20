package com.example.estudo.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.estudo.R
import com.example.estudo.databinding.SearchPageBinding
import com.example.estudo.viewModel.MainViewModel

class SearchActivity :  AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : SearchPageBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListeners()
    }

    fun setListeners() {
        binding.buttonSearch.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
    }

    fun setObserver() {
        mainVM.getShowTittle().observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Música encontrada", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SongActivity::class.java))
                finish()
            }

            else {
                Toast.makeText(this, "Nenhuma música encontrada", Toast.LENGTH_SHORT).show()
            }
        })

        mainVM.getBackLogin().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_search -> mainVM.requestArtist( binding.edittextArtista.text.toString() )
            R.id.img_back -> mainVM.backLogin()
        }
    }

}