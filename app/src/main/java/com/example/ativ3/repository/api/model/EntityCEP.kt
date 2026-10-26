package com.example.ativ3.repository.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EntityCEP: Serializable {

    @SerializedName("cep")
    var cep: String = ""

    @SerializedName("logradouro")
    var logradouro: String = ""

    @SerializedName("complemento")
    var complemento: String = ""

    @SerializedName("unidade")
    var unidade: String = ""

    @SerializedName("bairro")
    var bairro: String = ""

    @SerializedName("localidade")
    var localidade: String = ""

    @SerializedName("uf")
    var uf: String = ""

    @SerializedName("estado")
    var estado: String = ""

    @SerializedName("regiao")
    var regiao: String = ""

    @SerializedName("ddd")
    var ddd: String = ""

    @SerializedName("ibge")
    var ibge: String = ""

    @SerializedName("erro")
    var erro: Boolean = false

}