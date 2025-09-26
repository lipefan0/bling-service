package br.com.contis.blingservice.service

import br.com.contis.blingservice.client.BlingApiClient
import br.com.contis.blingservice.client.dto.order.BlingOrderResponseDTO
import br.com.contis.blingservice.config.RabbitMqConfig
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class BlingProcessingService(
    private val blingApiClient: BlingApiClient,
    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    suspend fun processOrderById(orderId: Long): BlingOrderResponseDTO {
        try {
            log.info("Iniciando o processamento do pedido com ID: $orderId")

            // Simulação de obtenção da chave da API (pode ser de um banco de dados ou configuração)
            val apiKey = "sua_chave_api_aqui"

            val orderResponse = blingApiClient.findOrderById(orderId, apiKey)

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