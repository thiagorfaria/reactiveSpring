package br.com.trf.reactive

class JokeResponse(
        val type: String,
        val value: JokeDetailsResponse
)

class JokeDetailsResponse(
        val id: Int,
        val joke: String,
        val categories: List<String>
)