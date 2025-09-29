package br.com.contis.blingservice.client.order.dto.order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingComissao(
    val base: BigDecimal? = null,
    val aliquota: BigDecimal? = null,
    val valor: BigDecimal? = null
)
