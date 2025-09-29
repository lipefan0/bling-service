package br.com.contis.blingservice.client.order

import br.com.contis.blingservice.client.order.dto.order.BlingListOrdeData
import br.com.contis.blingservice.client.order.dto.order.BlingOrderResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class VendaClient(
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

    suspend fun listOrders(apiKey: String): BlingListOrdeData {
        return webClient.get()
            .uri("/pedidos/vendas")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingListOrdeData>()
    }
}