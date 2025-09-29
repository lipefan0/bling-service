package br.com.contis.blingservice.client.order.dto.order

import br.com.contis.blingservice.client.shared.dto.BlingId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderParcela(
    val id: Long? = null,
    val dataVencimento: String? = null,
    val valor: BigDecimal? = null,
    val observacoes: String? = null,
    val caut: String? = null,
    val formaPagamento: BlingId? = null
)