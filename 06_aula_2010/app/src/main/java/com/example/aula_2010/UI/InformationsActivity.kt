package com.example.aula_2010.UI

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.decode.SvgDecoder
import coil.load
import com.bumptech.glide.Glide
import com.example.aula_2010.R
import com.example.aula_2010.databinding.ActivityInformationsBinding
import com.example.aula_2010.repository.model.BandeiraEntity
import com.example.estudo.repository.model.CEPEntity
import com.example.estudo.viewModel.MainViewModel

class InformationsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainVM: MainViewModel
    private lateinit var binding : ActivityInformationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)

        val dataCEP = intent.getSerializableExtra("CEP_DATA") as CEPEntity
        setup(dataCEP)

        setObserver()
        setListener()
    }

    fun getCorrectionEntity(atr: String): String {
        return if (atr.isNullOrBlank()) getString(R.string.default_asnwer) else atr
    }

    fun paintBrazilMap( uf : String ) {
        //binding.imgMapBrasil.
    }

    fun setup( entity: CEPEntity) {
        // bandeira
        mainVM.requestBandeira( entity.uf )

        // mapa
        paintBrazilMap( entity.uf )


        // first fold
        binding.textStateName.setText( getCorrectionEntity(entity.estado) )
        binding.textStateAbrev.setText( getCorrectionEntity(entity.uf) )

        // second fold
        binding.textCepValue.setText( getCorrectionEntity(entity.cep) )
        binding.textLogradouroValue.setText( getCorrectionEntity(entity.logradouro) )
        binding.textComplementoValue.setText( getCorrectionEntity(entity.complemento) )
        binding.textBairroValue.setText( getCorrectionEntity(entity.bairro) )
        binding.textCidadeValue.setText( getCorrectionEntity(entity.localidade) )
        binding.textEstadoValue.setText( getCorrectionEntity(entity.estado) )

        // last fold
        binding.textRegiaoValue.setText( getCorrectionEntity(entity.regiao) )
        // binding.textCapitalValue.setText( getCorrectionEntity(entity.capital) )
        binding.textDddValue.setText( getCorrectionEntity(entity.ddd) )
        binding.textIbgeValue.setText( getCorrectionEntity(entity.ibge) )
    }

    fun setObserver() {
        mainVM.getBandeira().observe(this, Observer {
            binding.imgFlag.load(it) {
                decoderFactory(SvgDecoder.Factory()) // Decodifica o arquivo SVG da URL
                placeholder(R.drawable.outline_arrow_upload_progress_24)
                error(R.drawable.outline_cancel_24)
            }
        })
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