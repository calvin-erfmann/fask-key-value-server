package org.calvin.erfmann.api.utils

import java.security.SecureRandom

class tokenGenerator {
    private val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    private val secureRandom = SecureRandom()

    /**
     * Generiert einen sicheren Token.
     * @param length Standardmäßig 32 Zeichen, kann aber angepasst werden.
     */
    fun generate(length: Int = 32): String {
        return (1..length)
            .map { CHARACTERS[secureRandom.nextInt(CHARACTERS.length)] }
            .joinToString("")
    }
}