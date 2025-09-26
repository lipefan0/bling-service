package br.com.contis.blingservice.client.dto.order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderDesconto(
    val valor: BigDecimal? = null,
    val unidade: String? = null
)
