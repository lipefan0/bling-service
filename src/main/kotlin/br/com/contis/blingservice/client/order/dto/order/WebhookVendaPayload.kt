package br.com.contis.blingservice.client.order.dto.order

import java.time.LocalDateTime

data class WebhookVendaPayload(
    val eventId: String,
    val date: LocalDateTime,
    val version: String,
    val event: String,
    val companyId: String,
    val data: WebhookVendaData
    )
