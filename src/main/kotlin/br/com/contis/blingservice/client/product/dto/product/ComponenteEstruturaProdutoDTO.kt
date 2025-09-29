package br.com.contis.blingservice.client.product.dto.product

data class ComponenteEstruturaProdutoDTO(
    val tipoEstoque: String?, // "F" Físico e "V" Virtual
    val lancamentoEstoque: String?, // "A" Produto e Componente, "M" Componente e "P" Produto
    val componentes: List<ProdutosComponentesDTO>?
)
