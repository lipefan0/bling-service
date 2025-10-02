package br.com.contis.blingservice.client.product.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CampoCustomizadoProdutoDTO(
    val id: Long?,
    val idVinculo: Long?,
    val valor: String?,
    val item: String?
)
