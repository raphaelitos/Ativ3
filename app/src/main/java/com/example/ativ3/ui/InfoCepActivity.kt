package com.example.ativ3.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ativ3.R
import com.example.ativ3.databinding.ActivityMainBinding
import com.example.ativ3.repository.api.model.EntityCEP

class InfoCepActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val endereco = IntentCompat.getSerializableExtra(
            intent,
            "endereco",
            EntityCEP::class.java
        ) ?: run {
            finish()
            return
        }
        // Obtém a sigla do estado retornada pelo ViaCEP.
        val uf = endereco.uf.lowercase()

        // Monta a URL da bandeira.
        val urlBandeira =
            "https://assets.codante.io/codante-apis/bandeiras-do-brasil/${uf}-full.svg"

        //binding.web
    }
}