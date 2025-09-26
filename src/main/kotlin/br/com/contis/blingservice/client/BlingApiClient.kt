package br.com.contis.blingservice.client

import br.com.contis.blingservice.client.dto.order.BlingOrderResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class BlingApiClient(
    private val webClientBuilder: WebClient.Builder,
    @Value("\${bling.api.url}") private val blingApiUrl: String
) {

    private val webClient = webClientBuilder.baseUrl(blingApiUrl).build()

    suspend fun findOrderById(orderId: Long, apiKey: String): BlingOrderResponseDTO {
        return webClient.get()
            .uri("/pedidos/vendas/$orderId")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingOrderResponseDTO>()
    }
}