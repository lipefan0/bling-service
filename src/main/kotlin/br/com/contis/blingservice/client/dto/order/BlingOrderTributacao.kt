package br.com.contis.blingservice.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderTributacao(
    val totalICMS: BigDecimal? = null,
    val totalIPI: BigDecimal? = null
)
