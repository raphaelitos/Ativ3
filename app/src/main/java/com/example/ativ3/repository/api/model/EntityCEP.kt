package com.example.ativ3.repository.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EntityCEP: Serializable {

    @SerializedName("cep")
    var cep: String = "01001-000"

    @SerializedName("logradouro")
    var logradouro: String = "Praça da Sé"

    @SerializedName("complemento")
    var complemento: String = "lado ímpar"

    @SerializedName("unidade")
    var unidade: String = ""

    @SerializedName("bairro")
    var bairro: String = "Sé"

    @SerializedName("localidade")
    var localidade: String = "São Paulo"

    @SerializedName("uf")
    var uf: String = "SP"

    @SerializedName("estado")
    var estado: String = "São Paulo"

    @SerializedName("regiao")
    var regiao: String = "Sudeste"

    @SerializedName("ddd")
    var ddd: String = "11"

    @SerializedName("erro")
    var erro: Boolean = false

}