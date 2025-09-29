package br.com.contis.blingservice.client.order.dto.order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderTransporteContato(
    val id: Long? = null,
    val nome: String? = null
)
