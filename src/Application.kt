package ru.arrow

import arrow.core.Either
import data.freesound.SoundDescriptorDto
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
import io.ktor.client.features.logging.*
import io.ktor.features.*
import ru.arrow.freesound.analyze.SoundAnalyze
import java.lang.Exception

//import arrow.core.computations


fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging)
    }

    val soundAnalyze = SoundAnalyze(client)

    runBlocking {
        with(soundAnalyze) {
            mfccCsvData(loadDescriptors("99226"))
        }
    }

    install(Authentication) {
        basic("myBasicAuth") {
            realm = "Ktor Server"
            validate { if (it.name == "test" && it.password == "password") UserIdPrincipal(it.name) else null }
        }
    }
    install(ContentNegotiation)

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
        get("/auth") {
            call.respondText("Введите email", contentType = ContentType.Text.Plain)
        }
        authenticate("myBasicAuth") {
            get("/protected/route/basic") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }
    }
}


fun arrowTest() {
    queryUser("2", "^\\d\$".toRegex())
        .fold({exp -> println(exp)}, {name -> println(name)})
    val name = queryUser("Ann", "^\\ddd\$".toRegex())
    val location = queryUser("Anus", "^\\ddddd\$".toRegex())
    val action = { name: String -> { location: String -> "Hello $name from $location" } }
    println(action(""))
}


fun queryUser(question: String, pattern: Regex): Either<String, String> {
    println(question)
    if(pattern.matches(question)) {
        return Either.Right(question)
    }
    return Either.Left("$question does not match $pattern")
}

data class JsonSampleClass(val hello: String)

