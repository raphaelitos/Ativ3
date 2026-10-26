package com.example.ativ3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ativ3.repository.api.RepositoryCEP
import com.example.ativ3.repository.api.model.EntityCEP

class MainViewModel : ViewModel() {

    private val repository = RepositoryCEP()

    // A ViewModel controla as alterações do estado.
    private val _estado = MutableLiveData<EstadoCEP>(EstadoCEP.Inicial)

    // A Activity possui acesso somente para observação.
    val estado: LiveData<EstadoCEP> = _estado

    fun buscarCEP(cepDigitado: String) {

        val cep = cepDigitado.trim().replace("-", "")

        if (!cep.matches(Regex("\\d{8}"))) {
            _estado.value = EstadoCEP.Erro("CEP inválido.")
            return
        }

        _estado.value = EstadoCEP.Carregando

        // Delega a consulta ao Repository.
        repository.buscarCEP(cep) { endereco, erro ->

            _estado.value = if (endereco != null) {
                EstadoCEP.Sucesso(endereco)
            } else {
                EstadoCEP.Erro(erro ?: "Erro desconhecido.")
            }
        }
    }

    // Impede que uma navegação seja repetida após uma rotação.
    fun consumirResultado() {
        _estado.value = EstadoCEP.Inicial
    }
}

// Possíveis estados da consulta.
sealed class EstadoCEP {

    object Inicial : EstadoCEP()

    object Carregando : EstadoCEP()

    data class Sucesso(val endereco: EntityCEP) : EstadoCEP()

    data class Erro(val mensagem: String) : EstadoCEP()
}