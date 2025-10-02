package br.com.contis.blingservice.client.credentials.dto

data class BlingTokenResponseDTO(
    val userId: String,
    val serviceName: String,
    val externalId: String,
    val details: AccessTokenDTO
)
