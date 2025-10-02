package br.com.contis.blingservice.client.order.dto.post

data class BlingCreateOrderContatoDTO(
    val id: Long,
    val tipoPessoa: String,
    val numeroDocumento: String
)
