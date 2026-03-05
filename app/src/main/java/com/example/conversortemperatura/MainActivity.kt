package com.example.conversortemperatura

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.conversortemperatura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val df = DecimalFormat("#.#")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCelsius.setOnClickListener {
            val inputText = binding.inpTemperatura.text
            if (inputText.isEmpty()) {
                binding.txtSemvalor.text = "⚠ Coloque um valor"
                Toast.makeText(applicationContext, "⚠ Coloque um valor", Toast.LENGTH_LONG).show()
            } else {
                if(binding.txtSemvalor.text.isNotEmpty())
                    binding.txtSemvalor.text = ""

                val fahrenheit = inputText.toString().toDouble()
                val celsius = df.format((fahrenheit - 32) * (5.0 / 9.0))

                binding.txtResultado.text = "${celsius}º C"
            }
        }

        binding.buttonFahren.setOnClickListener {
            val inputText = binding.inpTemperatura.text
            if (inputText.isEmpty()) {
                binding.txtSemvalor.text = "⚠ Coloque um valor"
                Toast.makeText(applicationContext, "⚠ Coloque um valor", Toast.LENGTH_LONG).show()
            } else {
                if(binding.txtSemvalor.text.isNotEmpty())
                    binding.txtSemvalor.text = ""

                val celsius = inputText.toString().toDouble()
                val fahrenheit = df.format( celsius * 1.8 + 32)

                binding.txtResultado.text = "${fahrenheit}º F"
            }
        }

        binding.ibtLixeira.setOnClickListener {
            binding.inpTemperatura.setText("")
            binding.txtResultado.text = ""
        }
    }
}