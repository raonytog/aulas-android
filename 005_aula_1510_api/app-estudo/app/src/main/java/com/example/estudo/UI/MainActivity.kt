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
import com.example.estudo.viewModel.MainViewModel
import com.example.estudo.databinding.LoginBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : LoginBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListeners()

        mainVM.main_setup()
    }

    fun setListeners() {
        binding.buttonJoin.setOnClickListener(this)
    }

    fun setObserver() {
        mainVM.getSkipLogin().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SearchActivity::class.java))
                finish()
            }
        })

        mainVM.getLogin().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SearchActivity::class.java))
                finish()
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_join -> mainVM.validateLogin(
                binding.edittextName.text.toString(),
                binding.edittextPassword.text.toString()
            )
        }
    }
}
