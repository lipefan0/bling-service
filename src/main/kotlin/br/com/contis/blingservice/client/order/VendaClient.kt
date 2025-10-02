package br.com.contis.blingservice.client.order

import br.com.contis.blingservice.client.order.dto.get.BlingListOrdeData
import br.com.contis.blingservice.client.order.dto.get.BlingOrderResponseDTO
import br.com.contis.blingservice.client.order.dto.post.BlingCreateOrderDTO
import br.com.contis.blingservice.client.order.dto.post.BlingCreateOrderResponseDTO
import br.com.contis.blingservice.client.order.dto.post.NfceResponseDTO
import br.com.contis.blingservice.client.order.dto.put.BlingUpdateOrderDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodilessEntity
import org.springframework.web.reactive.function.client.awaitBody

@Component
class VendaClient(
    private val webClientBuilder: WebClient.Builder,
    @Value("\${bling.api.url}") private val blingApiUrl: String
) {

    private val webClient = webClientBuilder.baseUrl(blingApiUrl).build()

    suspend fun createOrder(payload: BlingCreateOrderDTO, apiKey: String): BlingCreateOrderResponseDTO {
        return webClient.post()
            .uri("/pedidos/vendas")
            .header("Authorization", "Bearer $apiKey")
            .bodyValue(payload)
            .retrieve()
            .awaitBody<BlingCreateOrderResponseDTO>()
    }

    suspend fun releaseOrderStock(orderId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/lancar-estoque")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }

    suspend fun reverseOrderStock(orderId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/estornar-estoque")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }

    suspend fun postReceivables(orderId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/lancar-contas")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }

    suspend fun reverseReceivables(orderId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/estornar-contas")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }

    suspend fun postNfe(orderId: Long, apiKey: String): ResponseEntity<NfceResponseDTO>{
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/gerar-nfe")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody()
    }

    suspend fun postNfce(orderId: Long, apiKey: String): ResponseEntity<NfceResponseDTO> {
        return webClient.post()
            .uri("/pedidos/vendas/$orderId/gerar-nfce")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody()
    }

    suspend fun updateOrder(orderId: Long, payload: BlingUpdateOrderDTO, apiKey: String): BlingCreateOrderResponseDTO {
        return webClient.put()
            .uri("/pedidos/vendas/$orderId")
            .header("Authorization","Bearer $apiKey")
            .bodyValue(payload)
            .retrieve()
            .awaitBody<BlingCreateOrderResponseDTO>()
    }

    suspend fun updateOrderStatus(orderId: Long, statusId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.patch()
            .uri("/pedidos/vendas/$orderId/situacoes/$statusId")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }

    suspend fun findOrderById(orderId: Long, apiKey: String): BlingOrderResponseDTO {
        return webClient.get()
            .uri("/pedidos/vendas/$orderId")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingOrderResponseDTO>()
    }

    suspend fun findOrderByNumber(orderNumber: String, apiKey: String): BlingListOrdeData {
        return webClient.get()
            .uri("/pedidos/vendas?numero=${orderNumber}")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingListOrdeData>()
    }

    suspend fun listOrders(apiKey: String): BlingListOrdeData {
        return webClient.get()
            .uri("/pedidos/vendas")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBody<BlingListOrdeData>()
    }

    suspend fun deleteOrder(orderId: Long, apiKey: String): ResponseEntity<Void> {
        return webClient.delete()
            .uri("/pedidos/vendas/$orderId")
            .header("Authorization", "Bearer $apiKey")
            .retrieve()
            .awaitBodilessEntity()
    }


}