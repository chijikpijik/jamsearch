package ru.arrow

import arrow.core.Either
import arrow.*
import arrow.core.Option
import arrow.core.curried
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import kotlin.test.*
import io.ktor.server.testing.*


class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }

    @Test
    fun arrowTest() {
        val name = queryUser("Ann", "^\\ddd\$".toRegex())
        val location = queryUser("Anus", "^\\ddddd\$".toRegex())
        val action = { name: String -> { location: String -> "Hello $name from $location" } }
        val  cur = { name : String, location: String -> "Hello $name from $location" }
        cur.curried()
//        location.ap(name.map(action))
        println(action(""))
    }


    fun queryUser(question: String, pattern: Regex): Either<String, String> {
        println(question)
        if(pattern.matches(question)) {
            return Either.Right(question)
        }
        return Either.Left("$question does not match $pattern")
    }

}
