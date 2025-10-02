package br.com.contis.blingservice.service

import br.com.contis.blingservice.client.order.VendaClient
import br.com.contis.blingservice.client.order.dto.get.BlingOrderResponseDTO
import br.com.contis.blingservice.client.order.dto.post.BlingCreateOrderDTO
import br.com.contis.blingservice.client.order.dto.post.BlingCreateOrderResponseDTO
import br.com.contis.blingservice.client.order.dto.put.BlingUpdateOrderDTO
import br.com.contis.blingservice.config.RabbitMqConfig
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class BlingOrderService(
    private val vendaClient: VendaClient,
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    /*
    * Cria um serviço para criar pedido de venda no bling
    * o retorno chama o processOrderById para processar o pedido
    * */
    suspend fun createOrder(payload: BlingCreateOrderDTO, apiKey: String): BlingOrderResponseDTO {
        val createResponse = vendaClient.createOrder(payload, apiKey)
        val orderId = createResponse.data.id
        log.info("Pedido criado com sucesso no Bling. ID: {}, Número: {}", orderId, createResponse.data)
        return processOrderById(orderId, apiKey)
    }

    suspend fun updateOrder(orderId: Long, payload: BlingUpdateOrderDTO, apiKey: String): BlingOrderResponseDTO {
        val updateResponse = vendaClient.updateOrder(orderId, payload, apiKey)
        log.info("Pedido atualizado com sucesso no Bling. ID: {}, Número: {}", orderId, updateResponse.data.id)
        return processOrderById(orderId, apiKey)
    }

    suspend fun getOrderByNumber(orderNumber: String, apiKey: String): BlingOrderResponseDTO {
        val listResponse = vendaClient.findOrderByNumber(orderNumber, apiKey)
        log.info("Pedido buscado com sucesso no Bling. Número: {}, ID: {}", orderNumber, listResponse)
        val firstItem = listResponse.data.firstOrNull()
            ?: error("Order not found")

        val orderId = firstItem.id
        return processOrderById(orderId, apiKey)
    }

    suspend fun processOrderById(orderId: Long, apiKey: String): BlingOrderResponseDTO {
        try {
            log.info("Iniciando o processamento do pedido com ID: $orderId")

            val orderResponse = vendaClient.findOrderById(orderId, apiKey)

            log.info("Dados do pedido {} recebidos com sucesso. Número no Bling: {}", orderId, orderResponse.data.numero)

            // --- PRÓXIMOS PASSOS DA LÓGICA ENTRARIAM AQUI ---
            // Ex: Mapear para um novo evento, publicar no RabbitMQ, etc.

            rabbitTemplate.convertAndSend(
                RabbitMqConfig.BUSINESS_EVENT_EXCHANGE, // Para qual exchange enviar
                RabbitMqConfig.ORDER_READY_FOR_FULFILLMENT_ROUTING_KEY, // Qual a "etiqueta" de roteamento
                orderResponse.data // O objeto a ser enviado (será convertido para JSON)
            )
            log.info("Evento para o pedido {} publicado no exchange '{}'", orderId, RabbitMqConfig.BUSINESS_EVENT_EXCHANGE)


            // Retornamos os dados que buscamos
            return orderResponse
        } catch (e: Exception) {
            log.error("Falha ao processar o pedido com ID: {}. Causa: {}", orderId, e.message)
            // Lançar a exceção permite que o chamador decida como lidar com a falha (ex: NACK)
            throw e
        }
    }

}