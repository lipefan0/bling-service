package br.com.contis.blingservice.client.product.dto.product

import br.com.contis.blingservice.client.shared.dto.BlingId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class VariacaoProdutoResponseDTO(
    val id: Long,
    val nome: String,
    val codigo: String?,
    val preco: BigDecimal,
    val tipo: String,
    val situacao: String,
    val formato: String,
    val descricaoCurta: String?,
    val imagemURL: String?,
    val dataValidade: LocalDate?,
    val unidade: String?,
    val pesoLiquido: Float,
    val pesoBruto: Float?,
    val volumes: Int?,
    val itensPorCaixa: Int?,
    val gtin: String?,
    val gtinEmbalagem: String?,
    val tipoProducao: String?,
    val marca: String?,
    val descricaoComplementar: String?,
    val linkExterno: String?,
    val observacoes: String?,
    val descricaoEmbalagemDiscreta: String?,
    val categoria: BlingId?,
    val estoque: EstoqueEstruturaProdutoDTO?,
    val fornecedor: FornecedorProdutoDTO?,
    val dimensoes: DimensoesProdutoDTO?,
    val tributacao: TributacaoProdutoDTO?,
    val estrutura: ComponenteEstruturaProdutoDTO?,
    val camposCustomizados: List<CampoCustomizadoProdutoDTO>?,
    val variacoes: List<VariacaoProdutoResponseDTO>?
)