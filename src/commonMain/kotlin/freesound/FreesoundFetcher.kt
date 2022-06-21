package freesound

import freesound.data.Analysis
import freesound.data.Descriptors
import freesound.data.SoundDescriptorDto
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.json.*
import io.ktor.client.plugins.kotlinx.serializer.*
//import io.ktor.client.plugins.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement


class FreesoundFetcher {

    suspend fun loadAnalyzeData(data : Descriptors) {
        Napier.i(Json{
            ignoreUnknownKeys = true}.encodeToString(data))
//        val desc = Json{ignoreUnknownKeys = true}.encodeToJsonElement(Descriptors(mutableListOf("mfcc", "spectral_complexity")))
        withContext(Dispatchers.Default) {
            var result = getHttpClient(URLBuilder(port = 8081).build(), getClientEngine()).post("http://127.0.0.1:8081/analyze", {
                contentType(ContentType.Application.Json)
                setBody(data)
            })
            Napier.i(Json{ignoreUnknownKeys = true}.encodeToString(result), null, "Analyze result")
        }
    }

    suspend fun availableDescriptors() : Descriptors {
        var result : Descriptors
        withContext(Dispatchers.Default) {
            result = getHttpClient(URLBuilder(port = 8081).build(), getClientEngine()).get{url(path = "http://127.0.0.1:8081/availableDescriptors")}.body()
            Napier.i(Json{ignoreUnknownKeys = true}.encodeToString(result), null, "Analyze result")
        }
        return result
    }
}

internal expect fun getClientEngine() : HttpClientEngine

internal fun getHttpClient(url: Url, engine: HttpClientEngine): HttpClient = HttpClient(engine) {
    val format = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }
    install(JsonPlugin) {
        serializer = KotlinxSerializer(format)
    }
    defaultRequest {
        url {
            host = url.host
            protocol = url.protocol
        }
    }
}