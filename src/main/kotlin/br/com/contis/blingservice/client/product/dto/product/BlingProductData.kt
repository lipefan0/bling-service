package br.com.contis.blingservice.client.product.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingProductData(
    val id: Long,
    val idProdutoPai: Long?,
    val nome: String,
    val codigo: String?,
    val preco: BigDecimal,
    val precoCusto: BigDecimal?,
    val estoque: EstoqueSaldoVirtual,
    val tipo: String,
    val situacao: String,
    val formato: String,
    val descricaoCurta: String?,
    val imagemURL: String?
)
