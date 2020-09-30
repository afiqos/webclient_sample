package com.example.webclient_sample

import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class WebClientAPI {

    private val toEmail = "to@Email"
    private val fromEmail = "from@Email"
    private val emailSubject = "Sent from notification API"

    // 1. create web client instance
    private val webClient = WebClient.create("http://localhost:8080/customers")

    // 2. prepare request (within code below itself)

    // 3.read response
    fun getEmployeeById(): Mono<String> {
        return webClient
                .get()
                .uri("/1")
//                .retrieve()
//                .toEntity(Customer::class.java)
                .exchange()
                .doOnSuccess { clientResponse -> println("header = ${clientResponse.headers()}") }
                .flatMap { clientResponse -> clientResponse.bodyToMono(String::class.java) }
    }

    fun addNewCustomer(): Mono<ResponseEntity<String>> {
        return webClient
                .post()
                .uri("")
                .body(BodyInserters.fromValue(Customer(0, "goon", "gan", "goon", "123")))
                .retrieve()
//                .bodyToFlux(Customer::class.java)
                .toEntity(String::class.java)
                .doOnSuccess { args ->
                    println("Status code: ${args.statusCode}")
                    println("Message: ${args.body}")
                }
    }

    fun sendEmail(emailBody: String): Mono<ResponseEntity<String>> {
        val bodyContents = BodyInserters.fromValue(EmailParams(toEmail, fromEmail, emailSubject, emailBody))
        return webClient
                .post()
                .uri("/send")
                .body(bodyContents)
                .retrieve()
                .toEntity(String::class.java)
                .doOnSuccess { args ->
                    println("Status code: ${args.statusCode}")
                    println("Message: ${args.body}")
                }
    }
}

data class Customer(
        val accountNumber: Int,
        val name: String,
        val address: String,
        val username: String,
        val password: String
)

data class EmailParams(
        val to: String,
        val from: String,
        val subject: String,
        val body: String
)