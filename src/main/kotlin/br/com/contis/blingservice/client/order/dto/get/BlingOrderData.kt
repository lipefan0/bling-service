package br.com.contis.blingservice.client.order.dto.get

import br.com.contis.blingservice.client.shared.dto.BlingId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class BlingOrderData(
    val id: Long? = null,
    val numero: String? = null,
    val numeroLoja: String? = null,
    val data: String? = null,
    val dataSaida: String? = null,
    val dataPrevista: String? = null,
    val totalProdutos: BigDecimal? = null,
    val total: BigDecimal? = null,
    val contato: BlingOrderContato? = null,
    val situacao: BlingOrderSituacao? = null,
    val loja: BlingId? = null,
    val numeroPedidoCompra: String? = null,
    val outrasDespesas: BigDecimal? = null,
    val observacoes: String? = null,
    val observacoesInternas: String? = null,
    val desconto: BlingOrderDesconto? = null,
    val categoria: BlingId? = null,
    val notaFiscal: BlingId? = null,
    val tributacao: BlingOrderTributacao? = null,
    val itens: List<BlingOrderItem>? = null,
    val parcelas: List<BlingOrderParcela>? = null,
    val transporte: BlingOrderTransporte? = null,
    val vendedor: BlingId? = null,
    val taxas: BlingOrderTaxas? = null
)
