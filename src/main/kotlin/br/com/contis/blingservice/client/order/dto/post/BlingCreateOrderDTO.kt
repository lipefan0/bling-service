package br.com.contis.blingservice.client.order.dto.post

import br.com.contis.blingservice.client.order.dto.get.BlingOrderDesconto
import br.com.contis.blingservice.client.order.dto.get.BlingOrderTributacao
import br.com.contis.blingservice.client.shared.dto.BlingId
import java.math.BigDecimal
import java.time.LocalDate

data class BlingCreateOrderDTO(
    val numero: Int?,
    val numeroLoja: String?,
    val data: LocalDate,
    val dataSaida: LocalDate,
    val dataPrevista: LocalDate,
    val contato: BlingCreateOrderContatoDTO,
    val situacao: BlingId?,
    val loja: BlingId?,
    val numeroPedidoCompra: String?,
    val outrasDespesas: BigDecimal?,
    val observacoes: String?,
    val observacoesInternas: String?,
    val desconto: BlingOrderDesconto?,
    val categoria: BlingId?,
    val tributacao: BlingOrderTributacao?,
    val itens: List<BlingCreateOrderItemDTO>,
    val parcelas: List<BlingCreateOrderParcelaDTO>?,
    val transporte: BlingCreateOrderTransporteDTO?
)
