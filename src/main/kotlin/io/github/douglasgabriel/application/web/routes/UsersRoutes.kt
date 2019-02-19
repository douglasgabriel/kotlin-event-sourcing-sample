package io.github.douglasgabriel.application.web.routes

import io.github.douglasgabriel.application.web.controllers.UsersController
import io.javalin.apibuilder.ApiBuilder.patch
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class UsersRoutes : KoinComponent {

    private val usersController: UsersController by inject()

    fun register() {
        path("users") {
            post(usersController::create)
            patch(usersController::block)
        }
    }
}
