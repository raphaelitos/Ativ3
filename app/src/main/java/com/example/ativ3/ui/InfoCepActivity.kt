package com.example.ativ3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ativ3.R
import com.example.ativ3.databinding.ActivityInfoCepBinding
import com.example.ativ3.repository.api.model.EntityCEP
import java.util.Locale

class InfoCepActivity : AppCompatActivity() {

    // Binding correspondente ao activity_info_cep.xml.
    private lateinit var binding: ActivityInfoCepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoCepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Evita que o conteúdo fique atrás das barras do Android.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Recupera a entidade enviada pela MainActivity.
        val endereco = IntentCompat.getSerializableExtra(
            intent,
            "endereco",
            EntityCEP::class.java
        ) ?: run {
            finish()
            return
        }

        exibirEndereco(endereco)
    }

    private fun exibirEndereco(endereco: EntityCEP) {

        // O ViewBinding permite acessar os TextViews diretamente.
        binding.txtTituloEstado.text = mostrar(endereco.estado)

        binding.txtCep.text = getString(
            R.string.label_cep,
            mostrar(endereco.cep)
        )

        binding.txtLogradouro.text = getString(
            R.string.label_logradouro,
            mostrar(endereco.logradouro)
        )

        binding.txtBairro.text = getString(
            R.string.label_bairro,
            mostrar(endereco.bairro)
        )

        binding.txtCidade.text = getString(
            R.string.label_cidade,
            mostrar(endereco.localidade)
        )

        binding.txtUf.text = getString(
            R.string.label_uf,
            mostrar(endereco.uf)
        )

        binding.txtIbge.text = getString(
            R.string.label_ibge,
            mostrar(endereco.ibge)
        )

        binding.txtDdd.text = getString(
            R.string.label_ddd,
            mostrar(endereco.ddd)
        )

        // Monta a URL do SVG usando a UF retornada pelo ViaCEP.
        val uf = endereco.uf.lowercase(Locale.ROOT)

        if (uf.matches(Regex("[a-z]{2}"))) {
            val urlBandeira =
                "https://assets.codante.io/codante-apis/bandeiras-do-brasil/${uf}-full.svg"

            // O WebView renderiza a imagem SVG remota.
            binding.webBandeira.loadUrl(urlBandeira)
        }
    }

    // Evita apresentar campos vazios na interface.
    private fun mostrar(valor: String?): String {
        return valor?.takeIf { it.isNotBlank() } ?: "-"
    }
}