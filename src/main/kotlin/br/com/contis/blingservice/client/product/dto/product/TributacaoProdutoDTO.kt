package br.com.contis.blingservice.client.product.dto.product

import br.com.contis.blingservice.client.shared.dto.BlingId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class TributacaoProdutoDTO(
    val origem: Int?,
    val nFCI: String?,
    val ncm: String,
    val cest: String?,
    val codigoListaServicos: String?,
    val spedTipoItem: String?,
    val codigoItem: String?,
    val percentualTributos: BigDecimal?,
    val valorBaseSRetencao: BigDecimal?,
    val valorICMSSubstituto: BigDecimal?,
    val codigoExcecaoTipi: String?,
    val classeEnquadramentoIpi: String?,
    val valorIpiFixo: BigDecimal?,
    val codigoSeloIpi: String?,
    val valorPisFixo: BigDecimal?,
    val valorCofinsFixo: BigDecimal?,
    val codigoANP: String?,
    val descricaoANP: String?,
    val percentualGLP: BigDecimal?,
    val percentualGasNacional: BigDecimal?,
    val percentualGasImportado: BigDecimal?,
    val valorPartida: BigDecimal?,
    val tipoArmamento: Int?,
    val descricaoCompletaArmamento: String?,
    val dadosAdicionais: String,
    val grupoProduto: BlingId?
    )
