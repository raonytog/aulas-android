package com.example.aula_2010.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.aula_2010.R
import com.example.aula_2010.databinding.ActivitySearchCepBinding
import com.example.estudo.repository.model.CEPEntity
import com.example.estudo.viewModel.MainViewModel

class SearchCEP : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainVM: MainViewModel
    private lateinit var binding : ActivitySearchCepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListener()

    }

    fun validate( entity: CEPEntity) : Boolean {
        return entity.uf.isBlank()
    }

    fun setObserver() {
        mainVM.getCepResult().observe(this, Observer {
            if (validate(it)) {
                Toast.makeText(this, getString(R.string.toast_error_invalid_cep), Toast.LENGTH_SHORT).show()
                return@Observer
            }

            val intent = Intent(this, InformationsActivity::class.java)
            intent.putExtra("CEP_DATA", it)
            startActivity(intent)
        })

        mainVM.getErrorMensage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    fun setListener() {
        binding.buttonSearch.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_search -> mainVM.requestCep( binding.edittextCep.text.toString() )
        }
    }
}