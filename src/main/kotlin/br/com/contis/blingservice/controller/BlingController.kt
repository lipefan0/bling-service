package br.com.contis.blingservice.controller

import br.com.contis.blingservice.client.dto.order.BlingOrderResponseDTO
import br.com.contis.blingservice.service.BlingProcessingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/order")
class BlingController(
    private val processingService: BlingProcessingService
) {

    @GetMapping("/{orderId}")
    suspend fun getOrderById(@PathVariable orderId: Long): BlingOrderResponseDTO {
        return processingService.processOrderById(orderId)
    }
}