package org.calvin.erfmann.api.plugins



import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.calvin.erfmann.api.routes.getValue
import org.calvin.erfmann.api.routes.helloRoutes
import org.calvin.erfmann.api.routes.login
import org.calvin.erfmann.api.routes.setValue
import org.calvin.erfmann.stuff.authService
import org.calvin.erfmann.theGoodStuff.PoolManager

fun Application.configureRouting(authService: authService, poolManager: PoolManager) {
    routing {
        helloRoutes()
        login(authService)
        getValue(authService, poolManager)
        setValue(authService, poolManager)
    }
}


