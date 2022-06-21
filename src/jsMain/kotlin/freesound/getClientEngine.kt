package freesound

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

internal actual fun getClientEngine(): HttpClientEngine {
    return JsClient().create {
    }
}