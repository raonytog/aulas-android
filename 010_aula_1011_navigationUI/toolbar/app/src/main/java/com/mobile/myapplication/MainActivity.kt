package com.mobile.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mobile.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val navHostFrag by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.toolbar.inflateMenu(R.menu.menu_toolbar)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setBinding()
        setToolbar()
    }

    fun setBinding() {
        binding.toolbar.setOnMenuItemClickListener({
            when (it.itemId) {
                R.id.search -> { Toast.makeText(this, "Botao de busca", Toast.LENGTH_SHORT).show()  }
                R.id.info -> { Toast.makeText(this, "Botao de info", Toast.LENGTH_SHORT).show()  }
            }
            true
        })
    }

    fun setToolbar() {
        val appBarConfiguration = AppBarConfiguration(navHostFrag.navController.graph)
        binding.toolbar.setupWithNavController(navHostFrag.navController, appBarConfiguration)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_cara -> { navHostFrag.navController.navigate(R.id.action_initFragment_to_oneFragment) }
            R.id.button_coroa -> { navHostFrag.navController.navigate(R.id.action_initFragment_to_twoFragment) }

        }
    }
}
