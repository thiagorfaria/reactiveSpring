package br.com.trf.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class JokerService(@Autowired private val builder: RestTemplateBuilder) {

    private val restTemplate: RestTemplate = builder.build()
    private val webClient = WebClient.create("http://api.icndb.com")

    fun getJoke(first: String, last: String) : String {
        val url = "http://api.icndb.com/jokes/random?limitTo=[nerdy]&firstName=$first&lastName=$last"
        val responseJoke = restTemplate.getForObject(url, JokeResponse::class.java)

        return responseJoke?.value?.joke ?: ""
    }

    fun getJokeAsync(first: String, last: String) : Mono<String> {
        val path = "/jokes/random?limitTo=[nerdy]&firstName=$first&lastName=$last"

        return webClient.get()
                .uri(path, first, last)
                .retrieve()
                .bodyToMono(JokeResponse::class.java)
                .filter { it?.value?.joke != null }
                .map { it.value.joke }
    }
}