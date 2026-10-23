package com.example.aula_2010.UI

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aula_2010.R
import com.example.aula_2010.databinding.ActivityInformationsBinding
import com.example.estudo.repository.model.CEPEntity
import com.example.estudo.viewModel.MainViewModel

class InformationsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainVM: MainViewModel
    private lateinit var binding : ActivityInformationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataCEP = intent.getSerializableExtra("CEP_DATA") as CEPEntity
        setup(dataCEP)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListener()
    }

    fun setup( entity: CEPEntity) {
        // first fold
        binding.textStateName.setText( entity.estado )
        binding.textStateAbrev.setText( entity.uf )

        // second fold
        binding.textCepValue.setText( entity.cep )
        binding.textLogradouroValue.setText( entity.logradouro )
        binding.textComplementoValue.setText( entity.complemento )
        binding.textBairroValue.setText( entity.bairro )
        binding.textCidadeValue.setText( entity.localidade )
        binding.textEstadoValue.setText( entity.estado )

        // last fold
        binding.textRegiaoValue.setText( entity.regiao )
        // binding.textCapitalValue.setText( entity.capital )
        binding.textDddValue.setText( entity.ddd )
        binding.textIbgeValue.setText( entity.ibge )
    }

    fun setObserver() {

    }

    fun setListener() {
        binding.imgBack.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_back -> finish()
        }
    }
}