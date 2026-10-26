package com.example.ativ3.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ativ3.R
import com.example.ativ3.databinding.ActivityMainBinding
import com.example.ativ3.viewModel.EstadoCEP
import com.example.ativ3.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding conecta a Activity aos elementos XML.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // O Android gerencia o ciclo de vida da ViewModel.
        mainVM = ViewModelProvider(this)[MainViewModel::class.java]

        // Começa a observar mudanças no LiveData.
        setObserver()

        binding.btnObterDadosCep.setOnClickListener(this)
    }

    private fun setObserver() {
        mainVM.estado.observe(this, Observer{
            when (it) {

                EstadoCEP.Inicial -> {
                    binding.btnObterDadosCep.isEnabled = true
                }

                EstadoCEP.Carregando -> {
                    binding.btnObterDadosCep.isEnabled = false
                }

                is EstadoCEP.Sucesso -> {

                    mainVM.consumirResultado()

                    val intent = Intent(
                        this,
                        InfoCepActivity::class.java
                    )

                    intent.putExtra("endereco", it.endereco)
                    startActivity(intent)
                }

                is EstadoCEP.Erro -> {
                    binding.btnObterDadosCep.isEnabled = true

                    Toast.makeText(
                        this,
                        it.mensagem,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.btn_obter_dados_cep) {

            val cep = binding.cepInput.text.toString()

            // Apenas solicita a operação à ViewModel.
            mainVM.buscarCEP(cep)
        }
    }
}