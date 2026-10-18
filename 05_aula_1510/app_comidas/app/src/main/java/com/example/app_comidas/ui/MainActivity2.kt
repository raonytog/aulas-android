package com.example.aula_2909

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aula_2909.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var binding: ActivityMain2Binding;
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListeners()

        mainVM.setup_2()
    }

    private fun setListeners() {
        binding.imgApple.setOnClickListener(this)
        binding.imgPizza.setOnClickListener(this)
        binding.imgIcecream.setOnClickListener(this)
        binding.buttonGerarFrase.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)
        binding.textRandomTxt.setOnLongClickListener(this)
    }

    private fun updateFoodUI(food: String) {
        val yellowColor = ContextCompat.getColor(this, R.color.yellow)

        binding.imgApple.clearColorFilter()
        binding.imgPizza.clearColorFilter()
        binding.imgIcecream.clearColorFilter()

        when (food) {
            "apple" -> binding.imgApple.setColorFilter(yellowColor)
            "pizza" -> binding.imgPizza.setColorFilter(yellowColor)
            "icecream" -> binding.imgIcecream.setColorFilter(yellowColor)
        }
    }


    private fun setObserver() {
        mainVM.textName.observe(this) { it ->
            binding.textGreating.text = "Hello, $it"
        }

        mainVM.selectedFood.observe(this) { it ->
            updateFoodUI(it)
        }

        mainVM.textRandom.observe(this) { it ->
            binding.textRandomTxt.text = it
        }

        mainVM.navigateBack.observe(this) { it ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.img_apple -> mainVM.selectFood("apple")
            R.id.img_pizza -> mainVM.selectFood("pizza")
            R.id.img_icecream -> mainVM.selectFood("icecream")
            R.id.button_gerar_frase -> mainVM.generateRandomMensage()
            R.id.button_back -> mainVM.logout()
        }
    }

    override fun onLongClick(view: View?): Boolean {
        when (view?.id) {
            R.id.text_random_txt -> {
                mainVM.removeFromLists( binding.textRandomTxt.text.toString())
                return true
            }
        }
        return false
    }
}