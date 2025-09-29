package br.com.contis.blingservice.client.product.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class EstoqueEstruturaProdutoDTO(
    val minimo: Float?,
    val maximo: Float?,
    val crossdocking: Int?,
    val localizacao: String?,
    val saldoVirtualTotal: Float?
)
