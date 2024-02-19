package com.example.plugins

import com.example.domain.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import java.io.File

fun Application.configureSession() {
    install(Sessions) {
        val secretEncryptKey = hex("00112233445566778899acbbcccdeeff")
        val secretAuthKey = hex("960df554d706413a")
        cookie<UserSession>(
            name = "USER_SESSION",
            storage = directorySessionStorage(File(".sessions"))
        )   {

                transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretAuthKey))
        }
    }
}