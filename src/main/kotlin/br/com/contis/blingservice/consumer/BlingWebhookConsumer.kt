package br.com.contis.blingservice.consumer

import br.com.contis.blingservice.client.credentials.CredentialsClient
import br.com.contis.blingservice.client.order.dto.get.WebhookVendaPayload
import br.com.contis.blingservice.config.RabbitMqConfig
import br.com.contis.blingservice.service.BlingProcessingService
import com.rabbitmq.client.Channel
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class BlingWebhookConsumer(
    private val processingService: BlingProcessingService,
    private val credentialsClient: CredentialsClient
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(id = "blingWebhookConsumer", queues = [RabbitMqConfig.BLING_WEBHOOK_QUEUE])
    fun onBlingWebhookReceived(
        payload: WebhookVendaPayload,
        channel: Channel,
        @Header(AmqpHeaders.DELIVERY_TAG) deliveryTag: Long
    ) {
        log.info("MENSAGEM RECEBIDA (Thread: {}): {}", Thread.currentThread().name, payload)
        try {
            runBlocking {
                val credentialsData = credentialsClient.getCredentialsByExternalId(payload.companyId)?:
                    throw IllegalArgumentException("Credenciais para empresa ${payload.companyId} não encontradas")
                processingService.processOrderById(payload.data.id, credentialsData.details.accessToken)
            }
            channel.basicAck(deliveryTag, false)
            log.info("Mensagem para o pedido {} processada e confirmada (ACK) (Thread: {}).", payload.data.id, Thread.currentThread().name)
        } catch (e: Exception) {
            log.error("Erro ao processar a mensagem. Causa: {}", e.message)
            channel.basicNack(deliveryTag, false, true)
            log.warn("Mensagem rejeitada (NACK) e devolvida para a fila (Thread: {}).", Thread.currentThread().name)
        }
    }
}