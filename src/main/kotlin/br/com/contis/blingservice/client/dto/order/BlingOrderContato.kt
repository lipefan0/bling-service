package br.com.contis.blingservice.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderContato(
    val id: Long? = null,
    val nome: String? = null,
    val tipoPessoa: String? = null,
    val numeroDocumento: String? = null
)
