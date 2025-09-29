package br.com.contis.blingservice.client.product.dto.product

import br.com.contis.blingservice.client.order.dto.order.BlingOrderContato
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
