package br.com.contis.blingservice.client.order.dto.get

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderTransporteContato(
    val id: Long? = null,
    val nome: String? = null
)
