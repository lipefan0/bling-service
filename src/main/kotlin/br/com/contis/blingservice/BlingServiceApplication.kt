package br.com.contis.blingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlingServiceApplication

fun main(args: Array<String>) {
    runApplication<BlingServiceApplication>(*args)
}
