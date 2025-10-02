package br.com.contis.blingservice.client.product.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class DimensoesProdutoDTO(
    val largura: Double?,
    val altura: Double?,
    val profundidade: Double?,
    val unidadeMedida: Int?
)
