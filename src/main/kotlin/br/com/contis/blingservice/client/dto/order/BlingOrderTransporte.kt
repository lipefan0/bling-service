package br.com.contis.blingservice.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderTransporte(
    val fretePorConta: Int? = null,
    val frete: BigDecimal? = null,
    val quantidadeVolumes: Int? = null,
    val pesoBruto: BigDecimal? = null,
    val prazoEntrega: Int? = null,
    val contato: BlingOrderTransporteContato? = null,
    val etiqueta: BlingOrderEtiqueta? = null,
    val volumes: List<BlingOrderVolume>? = null
)
