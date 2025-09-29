package br.com.contis.blingservice.client.product.dto.product

import br.com.contis.blingservice.client.shared.dto.BlingId


data class BlingGenerateVariablesProductDTO(
    val produtoPai: BlingId,
    val atributos: List<BlingAtributoProductDTO>
)
