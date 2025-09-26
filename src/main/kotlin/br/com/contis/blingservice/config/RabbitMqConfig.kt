package br.com.contis.blingservice.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    companion object{
        // Topologia de ENTRADA (já existente)
        const val INGRESS_WEBHOOK_EXCHANGE = "ex.ingress_webhook"
        const val BLING_WEBHOOK_ROUTING_KEY = "bling.webhook.#"
        const val BLING_WEBHOOK_QUEUE = "q.bling.webhook.received"

        // --- NOVAS CONSTANTES PARA A SAÍDA ---
        // Exchange para onde publicaremos nossos eventos de negócio enriquecidos
        const val BUSINESS_EVENT_EXCHANGE = "ex.business_event"

        // Rota específica para o evento de pedido pronto para fulfillment
        const val ORDER_READY_FOR_FULFILLMENT_ROUTING_KEY = "order.ready.for.fulfillment"
    }

    @Bean
    fun ingressWebhookExchange(): TopicExchange = TopicExchange(INGRESS_WEBHOOK_EXCHANGE)

    @Bean
    fun blingWebhookBinding(blingWebhookQueue: Queue, ingressWebhookExchange: TopicExchange): Binding {
        return BindingBuilder.bind(blingWebhookQueue)
            .to(ingressWebhookExchange)
            .with(BLING_WEBHOOK_ROUTING_KEY)
    }

    @Bean
    fun blingWebHookQueue(): Queue {
        return QueueBuilder.durable(BLING_WEBHOOK_QUEUE).build()
    }


    // --- NOVOS Beans de SAÍDA ---
    // Não precisamos criar a fila aqui. O serviço consumidor (ex: ShopifyService)
    // será responsável por criar sua própria fila e se ligar (bind) a este exchange.
    // O BlingService só precisa saber para qual "endereço" (exchange + routing key) enviar.
    @Bean
    fun businessEventExchange(): TopicExchange = TopicExchange(BUSINESS_EVENT_EXCHANGE)

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        // Este conversor vai serializar/deserializar objetos para JSON
        return Jackson2JsonMessageConverter()
    }

}