package br.com.contis.blingservice.client.order.dto.get

import br.com.contis.blingservice.client.shared.dto.BlingId
import java.math.BigDecimal
import java.time.LocalDate

data class WebhookVendaData(
    val id: Long,
    val data: LocalDate,
    val numero: Long,
    val numeroLoja: String,
    val total: BigDecimal,
    val contato: BlingId,
    val vendedor: BlingId,
    val loja: BlingId
)
