package com.thechance.api.plugins

import com.thechance.api.endpoints.*
import com.thechance.api.endpoints.user.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.swagger.codegen.v3.generators.html.StaticHtmlCodegen
import java.io.File

fun Application.configureRouting() {

    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
        openAPI(path = "openapi", swaggerFile = "openapi/documentation.yaml") {
            codegen = StaticHtmlCodegen()
        }

        // Раздача статических файлов из папки uploads
        get("/uploads/{filename}") {
            val filename = call.parameters["filename"] ?: return@get call.respondText(
                "Missing file name",
                status = HttpStatusCode.BadRequest
            )
            val file = File("uploads/$filename")
            if (file.exists()) {
                call.respondFile(file)
            } else {
                call.respondText(
                    "File not found",
                    status = HttpStatusCode.NotFound
                )
            }
        }

        productsRoutes()
        categoryRoutes()
        marketsRoutes()
        userRoutes()
        ownerRoutes()
        adminRoutes()
        cartRoutes()
        orderRoutes()
        wishListRoutes()
        deleteAllTables()
        imageRouts()
        tokenRouts()
        couponRoutes()
        notificationRoutes()
        reviewRoutes()
    }
}