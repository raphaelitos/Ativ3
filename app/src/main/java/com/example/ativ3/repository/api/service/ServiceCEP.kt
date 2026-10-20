package com.example.ativ3.repository.api.service

import com.example.ativ3.repository.api.model.EntityCEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceCEP {
    @GET("{cep}/json/")
    fun getInfoCEP(@Path("cep")cep: String): Call<EntityCEP>
}