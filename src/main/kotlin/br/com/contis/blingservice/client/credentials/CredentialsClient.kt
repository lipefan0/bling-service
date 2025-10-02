package br.com.contis.blingservice.client.credentials

import br.com.contis.blingservice.client.credentials.dto.BlingTokenResponseDTO
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class CredentialsClient(
    private val webClientBuilder: WebClient.Builder,
    private val credentialsUri: String = "http://localhost:8085/credentials/internal",
    @Value("\${credentials.api.key}") private val apiKey: String
) {

    private val webClient = webClientBuilder.baseUrl(credentialsUri).build()

    suspend fun getCredentialsByExternalId(externalId: String): BlingTokenResponseDTO? {
        return webClient.get()
            .uri("/bling/{externalId}", externalId)
            .header("X-API-Key", apiKey)
            .retrieve()
            .bodyToMono(BlingTokenResponseDTO::class.java)
            .awaitSingleOrNull<BlingTokenResponseDTO>()
    }
}