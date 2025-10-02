package br.com.contis.blingservice.client.order.dto.post

import br.com.contis.blingservice.client.order.dto.get.BlingOrderEtiqueta
import br.com.contis.blingservice.client.order.dto.get.BlingOrderTransporteContato
import java.math.BigDecimal

data class BlingCreateOrderTransporteDTO(
    val fretePorConta: Int? = null,
    val frete: BigDecimal? = null,
    val quantidadeVolumes: Int? = null,
    val pesoBruto: BigDecimal? = null,
    val prazoEntrega: Int? = null,
    val contato: BlingOrderTransporteContato? = null,
    val etiqueta: BlingOrderEtiqueta? = null,
    val volumes: List<BlingCreateOrderVolumeDTO>? = null
)
