package org.calvin.erfmann.api.routes

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable
import org.calvin.erfmann.stuff.authService

@Serializable
data class LoginRequest(val password: String)


fun Route.login(authService: authService) {

    post("/login") {
        val request = call.receive<LoginRequest>()

        val token = authService.getToken(request.password)

        if (token != null) {
            call.respond(mapOf("token" to token))
        } else {
            call.respond(mapOf("error" to "Invalid password"))
        }
    }

}