package com.example.aula_2909

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aula_2909.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var binding: ActivityMain2Binding;
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = Preferences(this, applicationContext)
        binding.textGreating.text = "Hello, ${sp.getString("name")}"
        binding.imgApple.setOnClickListener(this)
        binding.imgPizza.setOnClickListener(this)
        binding.imgIcecream.setOnClickListener(this)
        binding.buttonGerarFrase.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        binding.textRandomTxt.setOnLongClickListener(this)

        if (sp.getString("food") == "pizza") { binding.imgPizza.setColorFilter(ContextCompat.getColor(this, R.color.yellow)) }
        else if (sp.getString("food") == "apple") { binding.imgApple.setColorFilter(ContextCompat.getColor(this, R.color.yellow)) }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.img_apple) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAboutApple()
            binding.imgApple.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            binding.imgPizza.clearColorFilter()
            binding.imgIcecream.clearColorFilter()

            sp.setString("food", "apple")
        }

        if (view?.id == R.id.img_pizza) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAboutPizza()
            binding.imgPizza.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            binding.imgApple.clearColorFilter()
            binding.imgIcecream.clearColorFilter()


            sp.setString("food", "pizza")
        }

        if (view?.id == R.id.img_icecream) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAboutPizza()
            binding.imgIcecream.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            binding.imgApple.clearColorFilter()
            binding.imgPizza.clearColorFilter()

            sp.setString("food", "pizza")
        }

        if (view?.id == R.id.button_gerar_frase) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAllRandom()
        }

        if (view?.id == R.id.button_back) {
            sp.setString("name", "")
            sp.setString("food", "")

            binding.imgApple.clearColorFilter()
            binding.imgPizza.clearColorFilter()
            binding.imgIcecream.clearColorFilter()

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onLongClick(view: View?): Boolean {
        var text: String? = " error !"
        val duration = Toast.LENGTH_SHORT

        if (view?.id == R.id.text_random_txt) {
            CuriosidadesAlimentares.delFromAll( binding.textRandomTxt.text.toString() )
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAllRandom()
            val toast = Toast.makeText(applicationContext, "Removida frase: ${binding.textRandomTxt.text.toString()}!", duration)
            toast.show()

            return true
        }

        return false
    }
}