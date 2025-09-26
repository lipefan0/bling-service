package br.com.contis.blingservice.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderParcela(
    val id: Long? = null,
    val dataVencimento: String? = null,
    val valor: BigDecimal? = null,
    val observacoes: String? = null,
    val caut: String? = null,
    val formaPagamento: BlingId? = null
)