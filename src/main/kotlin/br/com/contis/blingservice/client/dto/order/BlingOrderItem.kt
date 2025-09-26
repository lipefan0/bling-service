package br.com.contis.blingservice.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderItem(
    val id: Long? = null,
    val codigo: String? = null,
    val unidade: String? = null,
    val quantidade: BigDecimal? = null,
    val desconto: BigDecimal? = null,
    val valor: BigDecimal? = null,
    val aliquotaIPI: BigDecimal? = null,
    val descricao: String? = null,
    val descricaoDetalhada: String? = null,
    val produto: BlingId? = null,
    val comissao: BlingComissao? = null
)
