package com.example.ativ3.repository.api

import com.example.ativ3.repository.api.client.ClientCEP
import com.example.ativ3.repository.api.model.EntityCEP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryCEP {

    private val service = ClientCEP.createCEPService()

    fun buscarCEP(
        cep: String,
        callback: (EntityCEP?, String?) -> Unit
    ) {

        // Executa a requisição sem bloquear a interface.
        service.getInfoCEP(cep).enqueue(object : Callback<EntityCEP> {

            override fun onResponse(
                call: Call<EntityCEP>,
                response: Response<EntityCEP>
            ) {

                val endereco = response.body()

                when {
                    !response.isSuccessful ->
                        callback(null, "Erro HTTP: ${response.code()}")

                    endereco == null ->
                        callback(null, "Resposta vazia da API.")

                    endereco.erro ->
                        callback(null, "CEP não encontrado.")

                    endereco.cep.isBlank() ->
                        callback(null, "Resposta inválida da API.")

                    else ->
                        callback(endereco, null)
                }
            }

            override fun onFailure(
                call: Call<EntityCEP>,
                t: Throwable
            ) {
                callback(null, "Erro de conexão.")
            }
        })
    }
}