package br.com.contis.blingservice.client.order.dto.post

import br.com.contis.blingservice.client.order.dto.get.BlingComissao
import br.com.contis.blingservice.client.shared.dto.BlingId
import java.math.BigDecimal

data class BlingCreateOrderItemDTO(
    val codigo: String? = null,
    val unidade: String? = null,
    val quantidade: BigDecimal? = null,
    val desconto: BigDecimal? = null,
    val valor: BigDecimal? = null,
    val aliquotaIPI: BigDecimal? = null,
    val descricao: String? = null,
    val descricaoDetalhada: String? = null,
    val produto: BlingId? = null,
    val comissao: BlingComissao? = null
)
