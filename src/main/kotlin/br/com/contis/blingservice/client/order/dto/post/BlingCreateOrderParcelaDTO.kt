package br.com.contis.blingservice.client.order.dto.post

import br.com.contis.blingservice.client.shared.dto.BlingId
import java.math.BigDecimal

data class BlingCreateOrderParcelaDTO(
    val dataVencimento: String? = null,
    val valor: BigDecimal? = null,
    val observacoes: String? = null,
    val caut: String? = null,
    val formaPagamento: BlingId? = null
)
