package org.calvin.erfmann.stuff

import org.calvin.erfmann.api.utils.tokenGenerator

class authService(password: String) {
    val password: String = password

    var activeTokens = mutableListOf<String>()





    fun getToken(inputPassword: String): String?{
        if (inputPassword != password){
            return null
        }
        val token = tokenGenerator().generate()
        activeTokens.add(token)
        return token
    }

    fun isTokenValid(token: String): Boolean{
        return activeTokens.contains(token)
    }




}