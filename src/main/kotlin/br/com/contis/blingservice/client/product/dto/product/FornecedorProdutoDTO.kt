package br.com.contis.blingservice.client.product.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class FornecedorProdutoDTO(
    val id: Long,
    val contato: BlingProdutoContato,
    val codigo: String,
    val precoCusto: BigDecimal,
    val precoCompra: BigDecimal?
)
