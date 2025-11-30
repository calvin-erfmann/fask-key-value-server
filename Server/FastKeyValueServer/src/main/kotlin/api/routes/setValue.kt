package org.calvin.erfmann.api.routes

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import kotlinx.serialization.Serializable
import org.calvin.erfmann.stuff.authService
import org.calvin.erfmann.theGoodStuff.PoolManager


@Serializable
data class SetValueRequest(val token: String, val pool: String, val key: String, val value: String)


fun Route.setValue(authService: authService, poolManager: PoolManager) {

    post("/value") {
        val request = call.receive<SetValueRequest>()

        val isValid = authService.isTokenValid(request.token)

        if (isValid) {
            val pool = poolManager.getPool(request.pool)
            if (pool != null) {
                pool.setValueValue(request.key, request.value)
                call.respond(mapOf("status" to "success"))
            } else {
                poolManager.createPool(request.pool).setValueValue(request.key, request.value)
            }
        } else {
            call.respond(mapOf("error" to "Invalid token"))
        }
    }

}