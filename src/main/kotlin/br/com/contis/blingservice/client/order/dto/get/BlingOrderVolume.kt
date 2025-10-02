package br.com.contis.blingservice.client.order.dto.get

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderVolume(
    val id: Long? = null,
    val servico: String? = null,
    val codigoRastreamento: String? = null
)