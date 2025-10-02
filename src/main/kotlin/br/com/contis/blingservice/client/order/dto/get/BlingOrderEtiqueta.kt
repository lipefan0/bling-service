package br.com.contis.blingservice.client.order.dto.get

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderEtiqueta(
    val nome: String? = null,
    val endereco: String? = null,
    val numero: String? = null,
    val complemento: String? = null,
    val municipio: String? = null,
    val uf: String? = null,
    val cep: String? = null,
    val bairro: String? = null,
    val nomePais: String? = null
)
