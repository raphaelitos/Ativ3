package com.example.ativ3.repository.api.client

import com.example.ativ3.repository.api.service.ServiceCEP
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientCEP {
    class ClientRetrofit {
        companion object {
            private lateinit var INSTANCE: Retrofit
            private const val BASE_URL = "viacep.com.br/ws"

            private fun getClientInstance(): Retrofit {
                val http = OkHttpClient.Builder()
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(http.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return INSTANCE
            }
        }

        fun createBlogPostService(): ServiceCEP {
            return getClientInstance().create(ServiceCEP::class.java)
        }

    }

}