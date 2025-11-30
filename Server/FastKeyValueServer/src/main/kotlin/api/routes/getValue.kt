package org.calvin.erfmann.api.routes

import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kotlinx.serialization.Serializable
import org.calvin.erfmann.stuff.authService
import org.calvin.erfmann.theGoodStuff.PoolManager

@Serializable
data class GetValueRequest(val token: String, val pool: String, val key: String)


fun Route.getValue(authService: authService, poolManager: PoolManager) {

    get("/value") {
        val request = call.receive<GetValueRequest>()

        val isValid = authService.isTokenValid(request.token)
        if (isValid) {
            val pool = poolManager.getPool(request.pool)
            if (pool != null) {
                val value = pool.getValueValue(request.key)
                if (value != null) {
                    call.respond(mapOf("value" to value))
                } else {
                    call.respond(mapOf("error" to "Key not found"))
                }
            } else {
                call.respond(mapOf("error" to "Pool not found"))
            }
        } else {
            call.respond(mapOf("error" to "Invalid token"))
        }
    }

}