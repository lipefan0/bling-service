package br.com.contis.blingservice.client.order.dto.get

import br.com.contis.blingservice.client.shared.dto.BlingId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingListOrdeData(
    val id: Long,
    val numero: String,
    val data: LocalDate,
    val dataSaida: LocalDate?,
    val dataPrevista: LocalDate?,
    val totalProdutos: BigDecimal?,
    val total: BigDecimal?,
    val contato: BlingOrderContato?,
    val situacao: BlingOrderSituacao?,
    val loja: BlingId?
)
