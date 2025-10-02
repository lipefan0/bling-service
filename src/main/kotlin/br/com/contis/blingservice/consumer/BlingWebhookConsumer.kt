package br.com.contis.blingservice.consumer

import br.com.contis.blingservice.client.credentials.CredentialsClient
import br.com.contis.blingservice.client.order.dto.order.WebhookVendaPayload
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
            // Use runBlocking para aguardar a conclusão da corrotina.
            // A thread do listener ficará bloqueada até que o processamento termine,
            // garantindo que o ACK/NACK seja executado na mesma thread.
            runBlocking {
                val credentialsData = credentialsClient.getCredentialsByExternalId(payload.companyId)?:
                    throw IllegalArgumentException("Credenciais para empresa ${payload.companyId} não encontradas")
                processingService.processOrderById(payload.data.id, credentialsData.details.accessToken)
            }

            // 3. Se a linha acima terminou sem lançar exceção, o ACK é feito na thread original.
            channel.basicAck(deliveryTag, false)
            log.info("Mensagem para o pedido {} processada e confirmada (ACK) (Thread: {}).", payload.data.id, Thread.currentThread().name)

        } catch (e: Exception) {
            log.error("Erro ao processar a mensagem. Causa: {}", e.message)

            // 4. Se qualquer exceção ocorreu no service, o NACK é feito na thread original.
            channel.basicNack(deliveryTag, false, true)
            log.warn("Mensagem rejeitada (NACK) e devolvida para a fila (Thread: {}).", Thread.currentThread().name)
        }
    }
}