import arrow.core.Either
import com.google.gson.Gson
import data.server.Descriptors
import freesound.analyze.SoundAnalyze
import io.ktor.server.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.gson.*
import io.ktor.client.plugins.json.*
import io.ktor.client.plugins.logging.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*


fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/output.js") {}
    }
}

val serverScope = CoroutineScope(Dispatchers.IO)

fun main() {
    val client = HttpClient(CIO) {
        install(JsonPlugin) {
            serializer = GsonSerializer()
        }
        install(Logging)
    }
    embeddedServer(Netty, port = 8081, host = "127.0.0.1") {
//        install(ContentNegotiation) {
//            gson()
//        }
        routing {
            get("/") {
//                call.respondHtml {  }
            }
            get("/availableDescriptors") {
//                call.respondHtml(HttpStatusCode.OK, Descriptors(mutableListOf("mfcc", "spectral_complexity")))
            }
            post("/analyze") {
//                val desc = call.receive(Descriptors::class).descriptors
//                call.respond(SoundAnalyze(client).loadDescriptors("99226"))
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}