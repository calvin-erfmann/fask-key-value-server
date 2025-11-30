package org.calvin.erfmann.api.routes

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.serialization.Serializable




fun Route.helloRoutes() {
    route("/hello") {


        get {

            call.respond(mapOf("message" to "hallo du knecht"))
        }
    }
}