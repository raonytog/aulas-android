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
        when( uf ) {
            "AC" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ac)
            "AL" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_al)
            "AP" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ap)
            "AM" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_am)
            "BA" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ba)
            "CE" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ce)
            "DF" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_df)
            "ES" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_es)
            "GO" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_go)
            "MA" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ma)
            "MG" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_mg)
            "MS" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ms)
            "MT" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_mt)
            "PA" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_pa)
            "PB" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_pb)
            "PE" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_pe)
            "PI" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_pi)
            "PR" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_pr)
            "RJ" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_rj)
            "RN" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_rn)
            "RO" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_ro)
            "RR" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_rr)
            "RS" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_rs)
            "SC" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_sc)
            "SE" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_se)
            "SP" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_sp)
            "TO" -> binding.imgMapBrasil.setImageResource(R.drawable.mapa_to)
        }
    }

    fun setCapital( uf: String ) {
        when ( uf ) {
            "AC" -> binding.textCapitalValue.setText(R.string.ac_capital)
            "AL" -> binding.textCapitalValue.setText(R.string.al_capital)
            "AP" -> binding.textCapitalValue.setText(R.string.ap_capital)
            "AM" -> binding.textCapitalValue.setText(R.string.am_capital)
            "BA" -> binding.textCapitalValue.setText(R.string.ba_capital)
            "CE" -> binding.textCapitalValue.setText(R.string.ce_capital)
            "DF" -> binding.textCapitalValue.setText(R.string.df_capital)
            "ES" -> binding.textCapitalValue.setText(R.string.es_capital)
            "GO" -> binding.textCapitalValue.setText(R.string.go_capital)
            "MA" -> binding.textCapitalValue.setText(R.string.ma_capital)
            "MG" -> binding.textCapitalValue.setText(R.string.mg_capital)
            "MS" -> binding.textCapitalValue.setText(R.string.ms_capital)
            "MT" -> binding.textCapitalValue.setText(R.string.mt_capital)
            "PA" -> binding.textCapitalValue.setText(R.string.pa_capital)
            "PB" -> binding.textCapitalValue.setText(R.string.pb_capital)
            "PE" -> binding.textCapitalValue.setText(R.string.pe_capital)
            "PI" -> binding.textCapitalValue.setText(R.string.pi_capital)
            "PR" -> binding.textCapitalValue.setText(R.string.pr_capital)
            "RJ" -> binding.textCapitalValue.setText(R.string.rj_capital)
            "RN" -> binding.textCapitalValue.setText(R.string.rn_capital)
            "RO" -> binding.textCapitalValue.setText(R.string.ro_capital)
            "RR" -> binding.textCapitalValue.setText(R.string.rr_capital)
            "RS" -> binding.textCapitalValue.setText(R.string.rs_capital)
            "SC" -> binding.textCapitalValue.setText(R.string.sc_capital)
            "SE" -> binding.textCapitalValue.setText(R.string.se_capital)
            "SP" -> binding.textCapitalValue.setText(R.string.sp_capital)
            "TO" -> binding.textCapitalValue.setText(R.string.to_capital)
        }
    }

    fun setup( entity: CEPEntity) {
        // bandeira
        mainVM.requestBandeira( entity.uf )

        // mapa
        paintBrazilMap( entity.uf )

        // define as capitais q n vieram de api
        setCapital( entity.uf )

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