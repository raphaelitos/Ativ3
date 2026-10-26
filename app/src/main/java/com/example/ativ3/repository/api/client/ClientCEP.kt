package com.example.ativ3.repository.api.client

import com.example.ativ3.repository.api.service.ServiceCEP
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientCEP {

    companion object {
        private lateinit var INSTANCE: Retrofit

        private const val BASE_URL = "https://viacep.com.br/ws/"

        private fun getClientInstance(): Retrofit {

            if (!::INSTANCE.isInitialized) {

                val http = OkHttpClient.Builder()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return INSTANCE
        }

        // Conecta o cliente Retrofit à interface de serviço.
        fun createCEPService(): ServiceCEP {
            return getClientInstance().create(ServiceCEP::class.java)
        }
    }
}