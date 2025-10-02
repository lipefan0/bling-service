package br.com.contis.blingservice.client.order.dto.get

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderTaxas(
    val taxaComissao: BigDecimal? = null,
    val custoFrete: BigDecimal? = null,
    val valorBase: BigDecimal? = null
)
