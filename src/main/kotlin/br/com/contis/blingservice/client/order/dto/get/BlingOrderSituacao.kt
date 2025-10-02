package br.com.contis.blingservice.client.order.dto.get

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderSituacao(
    val id: Long? = null,
    val valor: Int? = null
)
