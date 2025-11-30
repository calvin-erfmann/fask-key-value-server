package org.calvin.erfmann.api

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import kotlinx.serialization.json.Json
import org.calvin.erfmann.api.plugins.configureRouting
import org.calvin.erfmann.stuff.authService
import org.calvin.erfmann.theGoodStuff.PoolManager
import java.time.Duration

class apiServ {





    var authService = authService("password")
    var poolManager = PoolManager()




    fun startServer(){
        embeddedServer(Netty, port = 9000, host = "0.0.0.0") {
            module()
        }.start(wait = true)
    }

    fun Application.module() {
        install(ContentNegotiation) { // SERVER Plugin mit Alias
            json()
        }
        install(WebSockets.Plugin) {
            pingPeriod = Duration.ofSeconds(15)
            timeout = Duration.ofSeconds(30)
            maxFrameSize = Long.MAX_VALUE
            masking = false
        }
        configureRouting(
          authService,
            poolManager
        )
    }
}