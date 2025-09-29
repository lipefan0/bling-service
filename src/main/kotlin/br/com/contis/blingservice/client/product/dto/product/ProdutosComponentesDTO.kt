package br.com.contis.blingservice.client.product.dto.product

import br.com.contis.blingservice.client.shared.dto.BlingId

data class ProdutosComponentesDTO(
    val produto: BlingId?,
    val quantidade: Float?
)
