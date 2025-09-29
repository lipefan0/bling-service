package br.com.contis.blingservice.client.product

import br.com.contis.blingservice.client.product.dto.product.BlingGenerateVariablesProductDTO
import br.com.contis.blingservice.client.product.dto.product.BlingProductByIdDTO
import br.com.contis.blingservice.client.product.dto.product.BlingProductDTO
import br.com.contis.blingservice.client.product.dto.product.VariacaoProdutoResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class ProductClientprivate(
    val webClientBuilder: WebClient.Builder,
    @Value("\${bling.api.url}") private val blingApiUrl: String
) {
    private val webClient = webClientBuilder.baseUrl(blingApiUrl).build()

    suspend fun findProductById(orderId: Long, apiKey: String): BlingProductByIdDTO {
        return webClient.get()
            .uri("/produtos/$orderId")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingProductByIdDTO>()
    }

    suspend fun findProductBySku(sku: List<String>, apiKey: String): BlingProductDTO {
        val skuParams = sku.joinToString("&") { "codigo[]=$it" }

        return webClient.get()
            .uri("/produtos?$skuParams")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingProductDTO>()
    }

    suspend fun listProducts(apiKey: String): BlingProductDTO {
        return webClient.get()
            .uri("/produtos")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingProductDTO>()
    }

    suspend fun createProduct(apiKey: String, productData: String): BlingProductByIdDTO {
        return webClient.post()
            .uri("/produtos")
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .bodyValue(productData)
            .retrieve()
            .awaitBody<BlingProductByIdDTO>()
    }

    suspend fun creteVariationProduct(apiKey: String, productData: BlingGenerateVariablesProductDTO): VariacaoProdutoResponseDTO {
        return webClient.post()
            .uri("/produtos/variacoes/atributos/gerar-combinacoes")
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .bodyValue(productData)
            .retrieve()
            .awaitBody<VariacaoProdutoResponseDTO>()
    }

}